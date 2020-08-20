
package Server.Model;

import Game.Game;
import java.net.*; 
import java.io.*; 
import Game.Packages.*;
import Game.Packages.Business.BusinessType;
import Game.PathCards.*;
import Game.PathCards.Action.ActionType;
import Game.Player;
import Game.WildCards.*;
import Game.WildCards.Collect.Giver;
import Game.WildCards.Pay.Receptor;
import Server.Controller.ServerController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

public class ServerThread extends Thread{
    
    private ServerController controller;
    private Socket client;
    private Server server;
    public Player player;
    private boolean running;

    public ServerThread(Socket client, Server server, ServerController controller, Player player) {
        this.controller = controller;
        this.client = client;
        this.server = server;
        this.player = player;
        this.running = true;
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                IPackage pack = (IPackage) input.readObject();
                switch (pack.getType()) {
                    case Message: 
                        for (ServerThread serverThread : server.clients.values())
                            this.sendPackage(pack, serverThread);
                        break;
                    case Turn:
                        Turn turn = (Turn) pack;
                        this.evaluateTurn(turn);
                        break;
                    case Figure:
                        Figure figure = (Figure) pack;
                        this.evaluateFigure(figure);
                        break;                       
                    case Move:
                        Move move = (Move) pack;
                        this.evaluateMove(move);
                        break;
                    case Transaction:
                        Transaction transaction = (Transaction) pack;
                        if (transaction.getRecieverId() != 0){
                            this.sendPackage(transaction, server.clients.get(transaction.getRecieverId()));
                        }
                        break;
                    case Business:
                        Business business = (Business) pack;
                        this.evaluatePurchase(business);
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
    
    public void sendPackage(IPackage pack, ServerThread serverThread) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(serverThread.client.getOutputStream());
            output.writeObject(pack);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void evaluateTurn(Turn turn){
        if (turn.isFirstTrurn()){
            int inicialThrow;
            Pair<Integer, Integer> roll;
            do{
                roll = Game.rollDice();
                inicialThrow = roll.getKey() + roll.getValue();
            }while(server.throwIsRepeated(inicialThrow));
            sendPackage(new Move(player.getId(), 0, roll, "You rolled to determine your turn", PathCardType.Action, false), this);
            server.inicialThrow[player.getId()-1] = new Pair(player.getId(), inicialThrow);
            server.playersReady[player.getId()-1] = true;
            controller.addStatus("Player " + player.getId() + " is ready");
        }
        else{
            if (server.currentTurn == server.playerAmount-1)
                server.currentTurn = 0;
            else
                server.currentTurn++;
            sendPackage(new Turn(), server.clients.get(server.playerOrder[server.currentTurn]));
        }
        
    }
    
    public void evaluateFigure(Figure figure){
        if (figure.isFinalAnswer())
            server.playerTokens[figure.getPlayerId()-1] = figure.getToken();
        else{
            for (int i = 0; i < server.playerAmount; i++){
                if (server.playerTokens[i] != null && server.playerTokens[i] == figure.getToken()){
                    sendPackage(new Figure(figure.getPlayerId(), figure.getToken(), false), this);
                    return;
                }
            }
            sendPackage(new Figure(figure.getPlayerId(), figure.getToken(), true), this);
        }
    }
    
    public void evaluateMove(Move move){
        Pair<Integer, Integer> roll = Game.rollDice();
        Pair<Integer, Boolean> newPosition = Game.movePlayer(move.getPosition(), roll.getKey()+roll.getValue(), 39, true);
        if (player.inJail()){
            if (roll.getKey() == roll.getValue()){
                player.leaveJail();
            }
            else{
                player.reduceJail();
                if (player.inJail()){
                    sendPackage(new Move(player.getId(), 10, roll, "You did not throw doubles", PathCardType.Action, false), this);
                    return;
                }
                else{
                    sendPackage(new Transaction(0, 50, "You must pay a fine of 50"),this);
                }
            }
        }
        if (newPosition.getValue()){
            sendPackage(new Transaction(player.getId(), 200, "You passed Go"), this);
            player.setFirstRoundComplete();
        }
        this.evaluatePosition(newPosition.getKey(), roll);
    }
        
    public void evaluatePosition(int newPosition, Pair<Integer, Integer> roll){
        IPathCard card = Game.getCardAt(server.board, newPosition);
        switch(card.getType()){
            case Property:
                Property property = (Property) card;
                if (property.isPurchasable()){
                    if (player.firstRoundIsComplete())
                        sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a purchasable property", card.getType(), true), this);
                    else
                        sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a property", card.getType(), false), this);
                }
                else{
                    sendPackage(new Move(player.getId(), newPosition, roll, "You landed on an owned property", card.getType(), false), this);
                    sendPackage(new Transaction(property.ownerId, property.getRent(player.hasMonopoly(property.color, property.monopolySize)), "You must pay " +  property.getRent(player.hasMonopoly(property.color, property.monopolySize))),this); 
                }
                break;
            case Utility:
                Utility utility = (Utility) card;
                if (utility.isPurchasable()){
                    if (player.firstRoundIsComplete())
                        sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a purchasable utility", card.getType(), true),this);
                    else
                        sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a utility", card.getType(), false),this);
                }
                else{
                    sendPackage(new Move(player.getId(), newPosition, roll, "You landed on an owned utility", card.getType(), false),this);
                    sendPackage(new Transaction(utility.ownerId, utility.getRent(player.utilityAmount())*(roll.getKey()+roll.getValue()), "You must pay " +  utility.getRent(player.utilityAmount())*(roll.getKey()+roll.getValue())),this);
                }
                break;
            case Station:
                Station station = (Station) card;
                if (station.isPurchasable()){
                    if (player.firstRoundIsComplete())
                        sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a purchasable station", card.getType(), true),this);
                    else
                        sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a station", card.getType(), false),this);
                }
                else{
                    sendPackage(new Move(player.getId(), newPosition, roll, "You landed on an owned station", card.getType(), false),this);
                    sendPackage(new Transaction(station.ownerId, station.getRent(player.stationAmount()), "You must pay " +  station.getRent(player.stationAmount())),this);
                }
                break;
            case Tax:
                Tax tax = (Tax) card;
                sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a tax", card.getType(), false), this);
                sendPackage(new Transaction(0, tax.taxAmount, "You must pay " + tax.taxAmount),this); 
                break;
            case CardDraw:
                CardDraw cardDraw = (CardDraw) card;
                IWildCard wildCard = Game.drawWildCard(server.board, cardDraw);
                sendPackage(new Move(player.getId(), newPosition, roll, "You landed on a " + cardDraw.type, card.getType(), false, wildCard, cardDraw.type), this);  
                int distance;
                Pair<Integer, Boolean> newerPosition;
                switch(wildCard.getType()){
                    case Advance:
                        Advance advance = (Advance) wildCard;
                        distance = Game.findDestination(server.board, advance.destination, newPosition);
                        newerPosition = Game.movePlayer(newPosition, distance, 39, advance.collectGo);
                        if (newerPosition.getValue()){
                            sendPackage(new Transaction(player.getId(), 200, "You passed Go"), this);
                            player.setFirstRoundComplete();
                        }
                        this.evaluatePosition(newerPosition.getKey(), roll);
                        return;
                    case AdvanceToNearest:
                        AdvanceToNearest advNearest = (AdvanceToNearest) wildCard;
                        distance = Game.findNearest(server.board, advNearest.destination, newPosition);
                        newerPosition = Game.movePlayer(newPosition, distance, 39, true);
                        if (newerPosition.getValue()){
                            sendPackage(new Transaction(player.getId(), 200, "You passed Go"), this);
                            player.setFirstRoundComplete();
                        }
                        this.evaluatePosition(newerPosition.getKey(), roll);
                        return;
                    case JailFree:
                        //send the card
                        break;
                    case GoBack:
                        GoBack goBack = (GoBack) wildCard;
                        newerPosition = Game.movePlayer(newPosition, (39 - newPosition) + goBack.spaces, 39, false);
                        this.evaluatePosition(newerPosition.getKey(), roll);
                        return;
                    case Pay:
                        Pay pay = (Pay) wildCard;
                        if (pay.receptor == Receptor.Bank)
                            sendPackage(new Transaction(0, pay.amount, "you must pay " + pay.amount), this);
                        else{
                            for (ServerThread serverThread : server.clients.values()){
                                if (serverThread != this)
                                    sendPackage(new Transaction(serverThread.player.getId(), pay.amount, "you must pay " + pay.amount), this);
                            }
                        }
                        break;
                    case PayRepair:
                        break;
                    case Collect:
                        Collect collect = (Collect) wildCard;
                        if (collect.giver == Giver.Bank)
                            sendPackage(new Transaction(player.getId(), collect.amount, "you collected " + collect.amount + " from " + collect.giver), this);
                        else{
                            for (ServerThread serverThread : server.clients.values()){
                                if (serverThread != this)
                                    sendPackage(new Transaction(player.getId(), collect.amount, "you must pay " + collect.amount), serverThread);
                            }
                        }
                        break;
                }
                break;
            case Action:
                Action action = (Action) card;
                if (action.type == ActionType.ToJail){
                    player.sendToJail();
                    sendPackage(new Move(player.getId(), 10, roll, "You go to jail", card.getType(), false), this);  
                }
                else{
                  sendPackage(new Move(player.getId(), newPosition, roll, "You landed on " + action.type, card.getType(), false), this);  
                }
                break;
        }
        for (ServerThread serverThread : server.clients.values()){
            if (serverThread.player.getId() != player.getId())
                this.sendPackage(new Move(player.getId(), newPosition), serverThread);
        }
        System.out.println("New Position: " + newPosition + " Type: " + card.getType());
    }

    public void evaluatePurchase(Business business){
        switch (business.getBusinessType()){
            case Purchase:
                IPurchasable card = (IPurchasable) Game.getCardAt(server.board, business.getPosition());
                card.buy(player.getId());
                player.properties.add(card);
                sendPackage(new Business(player.getId(), card, BusinessType.Update), this);
                for (ServerThread serverThread : server.clients.values()){
                    if (!serverThread.player.enemyProperties.containsKey(player.getId()))
                        serverThread.player.enemyProperties.put(player.getId(), new ArrayList<>());
                    serverThread.player.enemyProperties.get(player.getId()).add(card);
                    serverThread.sendPackage(new Business(player.getId(), card, BusinessType.Update), serverThread);
                }
                break;
            case Mortgage:
                break;
            case Update:
                //update cards after trades
                break;
  
        }
    }
    
    public void evaluateBuild(Build build){
        Property card = Game.findPropertyToBuild(server.board, build.getCard());
        if (player.hasMonopoly(card.color, card.monopolySize) && !card.hotel){
            if (card.houses+1 >= 5)
                card.hotel = true;
            else
                card.houses++;
            Game.updatePlayerBuilds(player, card);
            for (ServerThread serverThread : server.clients.values()){
                sendPackage(new Build(player.getId(), card), serverThread);
            }
        }
    }
} 

