
package Server.Model;

import Game.Board;
import Game.BoardGenerator;
import Game.Packages.Figure;
import Game.Packages.Turn;
import Game.PathCards.IPurchasable;
import Game.Player;
import Game.Token;
import Server.Controller.ServerController;
import Server.View.ServerDisplay;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

public class Server extends Thread{
    
    private ServerController controller;
    private boolean active;
    public HashMap<Integer, ServerThread> clients;
    public int playerAmount;
    public boolean[] playersReady;
    public int[] playerOrder;
    public int currentTurn;
    public Token[] playerTokens;
    public Pair<Integer, Integer>[] inicialThrow;
    public Board board;
    
    public Server() {
        this.controller = new ServerController(this, new ServerDisplay());
        this.clients = new HashMap<>();
        this.active = false;
        this.board = BoardGenerator.generateBoard();
    }
    
    @Override
    public void run(){
        this.active = true;
        this.playersReady = new boolean[playerAmount];
        this.inicialThrow = new Pair[playerAmount];
        this.playerOrder = new int[playerAmount];
        this.playerTokens = new Token[playerAmount];
        try {
            ServerSocket server = new ServerSocket(5000);
            controller.addStatus("Server Active: Waiting for Clients...");
            for (int i = 1; i <= playerAmount; i++){
                Socket client = server.accept();
                ServerThread serverThread = new ServerThread(client, this, controller, new Player(i, "Player " + i));
                clients.put(i, serverThread);
                this.sendInt(i, client);
                this.sendInt(playerAmount, client);
                this.sendBoard(client);
                serverThread.start();
                controller.addStatus("Client Connected");
                playersReady[i-1] = false;
            }
            controller.addStatus("Server Full"); 
            while (!playersAreReady()){
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sendTokens();
            orderPlayers();
            currentTurn = 0;
            controller.addStatus("Sending first turn to: " + playerOrder[currentTurn]);
            clients.get(playerOrder[currentTurn]).sendPackage(new Turn(), clients.get(playerOrder[currentTurn]));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isActive(){
        return active;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }
    
    public boolean playersAreReady(){
        for (int i = 0; i < playerAmount; i++){
            if (playersReady[i] == false)
                return false;
        }
        return true;
    }
    
    public boolean throwIsRepeated(int diceNum){
        for (int i = 0; i < playerAmount; i++){
            if (inicialThrow[i] != null && inicialThrow[i].getValue() == diceNum)
                return true;
        }
        return false;
    }
    
    public void orderPlayers(){ 
        for (int i = 1; i < playerAmount; i++) {  
            Pair<Integer, Integer> key = inicialThrow[i]; 
            int j = i - 1;  
            while (j >= 0 && inicialThrow[j].getValue() > key.getValue()) {  
                inicialThrow[j + 1] = inicialThrow[j];  
                j = j - 1;  
            }  
            inicialThrow[j + 1] = key;  
        }
        for (int i = 0; i < playerAmount; i++){
            this.playerOrder[i] = inicialThrow[(playerAmount-1)-i].getKey();
        }
    }
    
    public void sendTokens(){
        for (ServerThread serverThread : clients.values()){
            for (int i = 0; i < playerAmount; i++){
                serverThread.sendPackage(new Figure(i+1, playerTokens[i], true), serverThread);
            }
        }
    }
    
    public void sendInt(int id, Socket client) {
        try {
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.writeInt(id);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendBoard(Socket client){
        try {
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            output.writeObject(board);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

}
