
package Client.Controller;

import Client.Model.Client;
import Client.View.SelectionDisplay;
import Game.Packages.Figure;
import Game.Token;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionController implements ActionListener{
    
    private SelectionDisplay display;
    private ClientController controller;
    private Client client;
    private boolean tokenSelected;
    private boolean validSelection;
    private Token selectedToken;
    
    public SelectionController(SelectionDisplay display, ClientController controller, Client client) {
        this.display = display;
        this.controller = controller;
        this.client = client;
        this.tokenSelected = false;
        this.validSelection = false;
        this.innit();
    }
    
    private void innit(){
        display.jButton_Roll.addActionListener(this);
        display.jButton_Token1.addActionListener(this);
        display.jButton_Token2.addActionListener(this);
        display.jButton_Token3.addActionListener(this);
        display.jButton_Token5.addActionListener(this);
        display.jButton_Token4.addActionListener(this);
        display.jButton_Token6.addActionListener(this);
        display.jButton_Token8.addActionListener(this);
        display.jButton_Token9.addActionListener(this);
        display.jButton_Token10.addActionListener(this);
        display.jButton_Token7.addActionListener(this);
        display.jButton_Token12.addActionListener(this);
        display.jButton_Token11.addActionListener(this);
        display.setLocationRelativeTo(null);
    }
    
    public void showDisplay(){
        display.setVisible(true);
    }
    
    public void setValidSelection(boolean valid){
        if (valid){
            this.validSelection = valid;
            this.display.jTextField_Msg.setText("");
        }
        else{
            this.tokenSelected = false;
            this.display.jTextField_Msg.setText("This token has already been picked");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Roll)){
            if (validSelection){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), selectedToken, true));
                client.clientThread.player.setToken(selectedToken);
                display.setVisible(false);
                controller.showDisplay();
            }
        }
        if (e.getSource().equals(display.jButton_Token1)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Boat, false));
                tokenSelected = true;
                selectedToken = Token.Boat;
            }
        }
        if (e.getSource().equals(display.jButton_Token2)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Hat, false));
                tokenSelected = true;
                selectedToken = Token.Hat;
            }
        }
        if (e.getSource().equals(display.jButton_Token3)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Boot, false));
                tokenSelected = true;
                selectedToken = Token.Boot;
            }
        }
        if (e.getSource().equals(display.jButton_Token4)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Cannon, false));
                tokenSelected = true;
                selectedToken = Token.Cannon;
            }
        }
        if (e.getSource().equals(display.jButton_Token5)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Car, false));
                tokenSelected = true;
                selectedToken = Token.Car;
            }
        }
        if (e.getSource().equals(display.jButton_Token6)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Carriage, false));
                tokenSelected = true;
                selectedToken = Token.Carriage;
            }
        }
        if (e.getSource().equals(display.jButton_Token7)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Thimbal, false));
                tokenSelected = true;
                selectedToken = Token.Thimbal;
            }
        }
        if (e.getSource().equals(display.jButton_Token8)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Dog, false));
                tokenSelected = true;
                selectedToken = Token.Dog;
            }
        }
        if (e.getSource().equals(display.jButton_Token9)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Guitar, false));
                tokenSelected = true;
                selectedToken = Token.Guitar;
            }
        }
        if (e.getSource().equals(display.jButton_Token10)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Horse, false));
                tokenSelected = true;
                selectedToken = Token.Horse;
            }
        }
        if (e.getSource().equals(display.jButton_Token11)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Bag, false));
                tokenSelected = true;
                selectedToken = Token.Bag;
            }
        }
        if (e.getSource().equals(display.jButton_Token12)){
            if (!tokenSelected){
                client.clientThread.sendPackage(new Figure(client.clientThread.player.getId(), Token.Iron, false));
                tokenSelected = true;
                selectedToken = Token.Iron;
            }
        }
            
    }
   
}
