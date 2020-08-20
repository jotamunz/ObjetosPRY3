
package Client.Model;

import Game.Packages.*;
import Client.Controller.ClientController;
import Game.Game;
import Game.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

public class ClientThread extends Thread{
    
    private ClientController controller;
    private Socket client;
    public Player player;
    private boolean running;
    
    public ClientThread(Socket client, ClientController controller, Player player) {
        this.controller = controller;
        this.client = client;
        this.player = player;
        this.running = true;
    }
    
    @Override
    public void run(){
        while (running) {
            try {
                ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                IPackage pack = (IPackage) input.readObject();
                switch (pack.getType()) {
                    case Message: 
                        Message msg = (Message) pack;
                        controller.addMessage(msg.getName(), msg.getMessage());
                        break;
                    case Turn:
                        player.setTurn(true);
                        controller.activateButtons();
                        controller.addActivity("Its your turn to roll!");
                        break;
                    case Figure:
                        Figure figure = (Figure) pack;
                        if (figure.getPlayerId() != player.getId())
                            player.enemyTokens.put(figure.getPlayerId(), figure.getToken());
                        else
                            controller.selectionController.setValidSelection(figure.isFinalAnswer());
                        break;
                    case Move:
                        Move move = (Move) pack;
                        this.setMove(move);
                        break;
                    case Transaction:
                        Transaction transaction = (Transaction) pack;
                        this.resolveTransaction(transaction);
                        break;
                    case Business:
                        Business business = (Business) pack;
                        this.evaluateBusiness(business);
                        break;
                    case Build:
                        Build build = (Build) pack;
                        this.evaluateBuild(build);
                        break;
                }
            }
            catch(IOException | ClassNotFoundException e) { 
                 System.out.println(e); 
            }
        }
    }
    
    public void sendPackage(IPackage pack){
        try {
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            output.writeObject(pack);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setMove(Move move){
        if (move.getPlayerId() == player.getId()){
            controller.setLeftDice(move.getRollAmount().getKey());
            controller.setRightDice(move.getRollAmount().getValue()); 
            controller.addActivity(move.getActivity());
            player.setPosition(move.getPosition());
            switch (move.getCardType()){
                case CardDraw:
                    controller.drawWildCard(move.getDrawTye(), move.getWildCard());
                    break;
                default:
                    if (move.isPurchasable())
                        controller.activateButtonPurchase();
            }
        }
        else{
            if (player.enemyPositions.replace(move.getPlayerId(), move.getPosition()) == null){
                player.enemyPositions.put(move.getPlayerId(), move.getPosition());
            }
        }
        controller.drawPlayerPositions();
    }
    
    public void resolveTransaction(Transaction transaction){
        if (transaction.getRecieverId() == player.getId()){
            player.addMoney(transaction.getMoney());
            controller.updateMoney();
        }
        else{
            player.addDebt(new Pair(transaction.getRecieverId(), transaction.getMoney()));
            controller.showPayBox(transaction.getMoney(), transaction.getDescription());
        }
        controller.addActivity(transaction.getDescription());
    }
    
    public void evaluateBusiness(Business business){
        switch (business.getBusinessType()){
            case Purchase:
                break;
            case Mortgage:
                break;
            case Update:
                if (business.getPlayerId() == player.getId()){
                    player.properties.add(business.getCard());
                }
                else{
                    if (!player.enemyProperties.containsKey(business.getPlayerId()))
                        player.enemyProperties.put(business.getPlayerId(), new ArrayList<>());
                    player.enemyProperties.get(business.getPlayerId()).add(business.getCard());
                }
        }
    }
    
    public void evaluateBuild(Build build){
        if (build.getPlayerId() == player.getId()){
            Game.updatePlayerBuilds(player, build.getCard());
        }
        else{
            Game.updateEnemyBuilds(player, build.getCard(), build.getPlayerId());
        }
        controller.drawHousesBoard();
    }
}
