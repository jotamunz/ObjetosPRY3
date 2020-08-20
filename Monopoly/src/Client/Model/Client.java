
package Client.Model;

import Client.Controller.ClientController;
import Client.View.GameDisplay;
import Client.View.NamePromptDisplay;
import Game.Board;
import Game.Player;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    private ClientController controller;
    public ClientThread clientThread;
    public Board board;
    
    public Client(){
        this.controller = new ClientController(this, new GameDisplay(), new NamePromptDisplay());
    }
    
    public void connect(String clientName){
        try {
            Socket client = new Socket("localhost", 5000);
            int id = this.recieveInt(client);
            int playerAmount = this.recieveInt(client);
            this.board = this.recieveBoard(client);
            clientThread = new ClientThread(client, controller, new Player(id, clientName));
            clientThread.start();
            controller.welcomePlayer(id);
            controller.showEnemyTabs(playerAmount);
            controller.updateMoney();      
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int recieveInt(Socket client){
        try {
            DataInputStream input = new DataInputStream(client.getInputStream());
            return input.readInt();
        } catch(IOException e) { 
            System.out.println(e); 
            return 0;
        }
    }
    
    public Board recieveBoard(Socket client){
        try {
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            return (Board) input.readObject();
        } catch(IOException e) { 
            System.out.println(e); 
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
