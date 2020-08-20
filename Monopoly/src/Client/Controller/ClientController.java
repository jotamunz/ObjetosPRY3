
package Client.Controller;

import Client.Model.Client;
import Game.Packages.*;
import Client.View.*;
import Game.Game;
import Game.Packages.Business.BusinessType;
import Game.PathCards.*;
import Game.PathCards.CardDraw.DrawType;
import static Game.PathCards.PathCardType.Property;
import Game.Token;
import Game.WildCards.IWildCard;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.text.DefaultCaret;

public class ClientController implements ActionListener, MouseListener{
    
    private Client client;
    private GameDisplay display;
    private NamePromptDisplay namePrompt;
    public SelectionController selectionController;
    public BuildController buildController;
    public TradeController tradeController;
    public MortgageController mortgageController;
    private int personIndexPropertyCards[] = {0,0,0,0,0,0,0}; 
    private IPurchasable currentCardSelected = null;
    
    public ClientController(Client client, GameDisplay display,NamePromptDisplay namePrompt){
        this.client = client;
        this.display = display;
        this.namePrompt = namePrompt;
        this.selectionController = new SelectionController(new SelectionDisplay(), this, client);
        this.buildController = new BuildController(new BuildDisplay(), this, client);
        this.tradeController = new TradeController(new TradeDisplay(), this, client);
        this.mortgageController = new MortgageController(new MortgageDisplay(), this, client);
        this.innit();
    }
    
    public void innit(){
        DefaultCaret caret = (DefaultCaret) display.jTextArea_Chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        display.jButton_Send.addActionListener(this);
        namePrompt.jButton_Go.addActionListener(this);
        this.init_buttoms();
        display.jPanel_BGMenu.addMouseListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        namePrompt.setLocationRelativeTo(null);
        namePrompt.setVisible(true);
    }
    
    private void init_buttoms()
    {
        display.jButtom_Slots0.addActionListener(this);
        display.jButtom_Slots1.addActionListener(this);
        display.jButtom_Slots2.addActionListener(this);
        display.jButtom_Slots3.addActionListener(this);
        display.jButtom_Slots4.addActionListener(this);
        display.jButtom_Slots5.addActionListener(this);
        display.jButtom_Slots6.addActionListener(this);
        display.jButtom_Slots7.addActionListener(this);
        display.jButtom_Slots8.addActionListener(this);
        display.jButtom_Slots9.addActionListener(this);
        display.jButtom_Slots10.addActionListener(this);
        display.jButtom_Slots11.addActionListener(this);
        display.jButtom_Slots12.addActionListener(this);
        display.jButtom_Slots13.addActionListener(this);
        display.jButtom_Slots14.addActionListener(this);
        display.jButtom_Slot15.addActionListener(this);
        display.jButtom_Slot16.addActionListener(this);
        display.jButtom_Slot17.addActionListener(this);
        display.jButtom_Slots18.addActionListener(this);
        display.jButtom_Slots19.addActionListener(this);
        display.jButtom_Slots20.addActionListener(this);
        display.jButtom_Slot21.addActionListener(this);
        display.jButtom_Slot22.addActionListener(this);
        display.jButtom_Slot23.addActionListener(this);
        display.jButtom_Slot24.addActionListener(this);
        display.jButtom_Slot25.addActionListener(this);
        display.jButtom_Slot26.addActionListener(this);
        display.jButtom_Slot27.addActionListener(this);
        display.jButtom_Slots28.addActionListener(this);
        display.jButtom_Slots29.addActionListener(this);
        display.jButtom_Slots30.addActionListener(this);
        display.jButtom_Slot31.addActionListener(this);
        display.jButtom_Slot32.addActionListener(this);
        display.jButtom_Slot33.addActionListener(this);
        display.jButtom_Slot34.addActionListener(this);
        display.jButtom_Slot35.addActionListener(this); 
        //FUNCTIONS
        display.jButton_Build.addActionListener(this);
        display.jButton_EndTurn.addActionListener(this);
        display.jButton_RollDice.addActionListener(this);
        display.jButton_Mortgage.addActionListener(this);
        display.jButton_Send.addActionListener(this);
        display.jButton_Trade.addActionListener(this);
        display.jButton_Bankrupt.addActionListener(this);
        display.jButton_Pay.addActionListener(this);
        display.jButton_Purchase.addActionListener(this);
        //PANEL BUTTOMS
        display.jButton_RightPropertyPlayer.addActionListener(this);
        display.jButton_LeftPropertyPlayer.addActionListener(this);
        display.jButton_RightPropertyEnemy1.addActionListener(this);
        display.jButton_LeftPropertyEnemy1.addActionListener(this);
        display.jButton_RightPropertyEnemy2.addActionListener(this);
        display.jButton_LeftPropertyEnemy2.addActionListener(this);
        display.jButton_RightPropertyEnemy3.addActionListener(this);
        display.jButton_LeftPropertyEnemy3.addActionListener(this);
        display.jButton_RightPropertyEnemy4.addActionListener(this);
        display.jButton_LeftPropertyEnemy4.addActionListener(this);
        display.jButton_RightPropertyEnemy5.addActionListener(this);
        display.jButton_LeftPropertyEnemy5.addActionListener(this);
        display.jButton_RightPropertyEnemy6.addActionListener(this);
        display.jButton_LeftPropertyEnemy6.addActionListener(this);
        
        display.jButton_RightStationPlayer.addActionListener(this);
        display.jButton_LeftStationPlayer.addActionListener(this);
        display.jButton_RightStationEnemy1.addActionListener(this);
        display.jButton_LeftStationEnemy1.addActionListener(this);
        display.jButton_RightStationEnemy2.addActionListener(this);
        display.jButton_LeftStationEnemy2.addActionListener(this);
        display.jButton_RightStationEnemy3.addActionListener(this);
        display.jButton_LeftStationEnemy3.addActionListener(this);
        display.jButton_RightStationEnemy4.addActionListener(this);
        display.jButton_LeftStationEnemy4.addActionListener(this);
        display.jButton_RightStationEnemy5.addActionListener(this);
        display.jButton_LeftStationEnemy5.addActionListener(this);
        display.jButton_RightStationEnemy6.addActionListener(this);
        display.jButton_LeftStationEnemy6.addActionListener(this);
        
        display.jButton_RightUtilityPlayer.addActionListener(this);
        display.jButton_LeftUtilityPlayer.addActionListener(this);
        display.jButton_RightUtilityEnemy1.addActionListener(this);
        display.jButton_LeftUtilityEnemy1.addActionListener(this);
        display.jButton_RightUtilityEnemy2.addActionListener(this);
        display.jButton_LeftUtilityEnemy2.addActionListener(this);
        display.jButton_RightUtilityEnemy3.addActionListener(this);
        display.jButton_LeftUtilityEnemy3.addActionListener(this);
        display.jButton_RightUtilityEnemy4.addActionListener(this);
        display.jButton_LeftUtilityEnemy4.addActionListener(this);
        display.jButton_RightUtilityEnemy5.addActionListener(this);
        display.jButton_LeftUtilityEnemy5.addActionListener(this);
        display.jButton_RightUtilityEnemy6.addActionListener(this);
        display.jButton_LeftUtilityEnemy6.addActionListener(this);
        this.deactivateButtons();
    }
    
    public void setPropertyText(JPropertyCard card_GUI, Property card_Logic)
    {
        this.currentCardSelected = (IPurchasable)card_Logic;
        card_GUI.jLabel_Name.setText(card_Logic.name);
        card_GUI.jLabel_Name.setBackground(card_Logic.color);
        card_GUI.jLabel_0Houses.setText("   Rent $"+card_Logic.rent[0]);
        card_GUI.jLabel_1HouseRnt.setText("With " + 1 + " House                      $" + card_Logic.rent[1]);
        card_GUI.jLabel_2HouseRnt.setText("With " + 2 + " Houses                     $" + card_Logic.rent[2]);
        card_GUI.jLabel_3HouseRnt.setText("With " + 3 + " Houses                     $" + card_Logic.rent[3]);
        card_GUI.jLabel_4HouseRnt.setText("With " + 4 + " Houses                     $" + card_Logic.rent[4]);
        card_GUI.jLabel_HotelRnt.setText("   With Hotel $"+card_Logic.rent[5]);
        card_GUI.jLabel_Morgage.setText("Mortgage value $"+card_Logic.mortgage);
        card_GUI.jLabel_HosesCst.setText("Houses cost $"+card_Logic.buildPrice + " each.");
        card_GUI.jLabel_HotelsCst1.setText("Hotels, $"+card_Logic.buildPrice + ". plus 4 houses");
    }
   
    
    public void setStationText(JStationCard card_GUI, Station card_Logic){
        this.currentCardSelected = (IPurchasable)card_Logic;
        card_GUI.jLabel_Name.setText(card_Logic.name);
    }
    
    public void setUtilityText(JUtilityCard card_GUI, Utility card_Logic)
    {
        this.currentCardSelected = (IPurchasable)card_Logic;
        card_GUI.jLabel_Name.setText(card_Logic.name);
        if(card_Logic.name.equals("Water"))
        {
            ImageIcon icon = new ImageIcon("Assets/UtilityImages/water.png"); 
            card_GUI.jLabel_UtilityImage.setIcon(icon);
        } else {
            ImageIcon icon = new ImageIcon("Assets/UtilityImages/electricity.png"); 
            card_GUI.jLabel_UtilityImage.setIcon(icon);
        }
    }
    
    public void setChanceText(JChanceCard card_GUI, IWildCard card_Logic){
        card_GUI.jTextArea_Desc.setText(card_Logic.getDesc());
    }
    
    public void setCommunityText(JCommunityCard card_GUI, IWildCard card_Logic){
        card_GUI.jTextArea_Desc.setText(card_Logic.getDesc());
    }    
    
    public void showDisplay(){
        display.setVisible(true);
        this.drawPlayerPositions();
        this.drawHousesBoard();
        this.updateMoney();
        client.clientThread.sendPackage(new Turn(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(namePrompt.jButton_Go)){
            if (!"".equals(namePrompt.jTextField_Name.getText())){
                client.connect(namePrompt.jTextField_Name.getText());
                namePrompt.hide();
                selectionController.showDisplay();
            }
        }
        else if (e.getSource().equals(display.jButton_Send)){
            if (!"".equals(display.jTextField_Chat.getText())){
                client.clientThread.sendPackage(new Message(client.clientThread.player.getName(), display.jTextField_Chat.getText()));
                display.jTextField_Chat.setText("");
            }
        }
        //PROPERTY TILES
        else if (e.getSource().equals(display.jButtom_Slots0))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[1]);
        else if (e.getSource().equals(display.jButtom_Slots2))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[3]);
        else if (e.getSource().equals(display.jButtom_Slots5))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[6]);
        else if (e.getSource().equals(display.jButtom_Slots7))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[8]);
        else if (e.getSource().equals(display.jButtom_Slots8))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[9]);
        else if (e.getSource().equals(display.jButtom_Slots9))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[11]);
        else if (e.getSource().equals(display.jButtom_Slots11))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[13]);
        else if (e.getSource().equals(display.jButtom_Slots12))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[14]);
        else if (e.getSource().equals(display.jButtom_Slots14))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[16]);
        else if (e.getSource().equals(display.jButtom_Slot16))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[18]);
        else if (e.getSource().equals(display.jButtom_Slot17))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[19]);
        else if (e.getSource().equals(display.jButtom_Slots18))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[21]);
        else if (e.getSource().equals(display.jButtom_Slots20))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[23]);
        else if (e.getSource().equals(display.jButtom_Slot21))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[24]);
        else if (e.getSource().equals(display.jButtom_Slot23))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[26]);
        else if (e.getSource().equals(display.jButtom_Slot24))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[27]);
        else if (e.getSource().equals(display.jButtom_Slot26))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[29]);
        else if (e.getSource().equals(display.jButtom_Slot27))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[31]);
        else if (e.getSource().equals(display.jButtom_Slots28))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[32]);
        else if (e.getSource().equals(display.jButtom_Slots30))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[34]);
        else if (e.getSource().equals(display.jButtom_Slot33))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[37]);
        else if (e.getSource().equals(display.jButtom_Slot35))
            this.setPropertyText(display.jPropertyCard ,(Property) client.board.board[39]);
        //STATION TILES
        else if (e.getSource().equals(display.jButtom_Slots4))
            this.setStationText(display.jStationCard ,(Station) client.board.board[5]);
        else if (e.getSource().equals(display.jButtom_Slots13))
            this.setStationText(display.jStationCard ,(Station) client.board.board[15]);
        else if (e.getSource().equals(display.jButtom_Slot22))
            this.setStationText(display.jStationCard ,(Station) client.board.board[25]);
        else if (e.getSource().equals(display.jButtom_Slot31))
            this.setStationText(display.jStationCard ,(Station) client.board.board[35]);
        //UTILITY TILES
        else if (e.getSource().equals(display.jButtom_Slots10))
            this.setUtilityText(display.jUtilityCard ,(Utility) client.board.board[12]);
        else if (e.getSource().equals(display.jButtom_Slot25))
            this.setUtilityText(display.jUtilityCard ,(Utility) client.board.board[28]);
        //ARROW BUTTOMS
        else if (e.getSource().equals(display.jButton_RightPropertyPlayer)){
            this.viewPropertiesRight(display.jPropertyCard_Player, 0);
        }
        else if (e.getSource().equals(display.jButton_LeftPropertyPlayer)){
            this.viewPropertiesLeft(display.jPropertyCard_Player, 0);
        }
        else if (e.getSource().equals(display.jButton_RightStationPlayer)){
            this.viewStationsRight(display.jStationCard_Player, 0);
        }
        else if (e.getSource().equals(display.jButton_LeftStationPlayer)){
            this.viewStationsLeft(display.jStationCard_Player, 0);
        }
        else if (e.getSource().equals(display.jButton_RightUtilityPlayer)){
            this.viewUtilitiesRight(display.jUtilityCard_Player, 0);
        }
        else if (e.getSource().equals(display.jButton_LeftUtilityPlayer)){
            this.viewUtilitiesLeft(display.jUtilityCard_Player, 0);
        }
        else if (e.getSource().equals(display.jButton_RightPropertyEnemy1)){
            this.viewPropertiesRight(display.jPropertyCard_Enemy1, 1);
        }
        else if (e.getSource().equals(display.jButton_LeftPropertyEnemy1)){
            this.viewPropertiesLeft(display.jPropertyCard_Enemy1, 1);
        }
        else if (e.getSource().equals(display.jButton_RightUtilityEnemy1)){
            this.viewUtilitiesRight(display.jUtilityCard_Enemy1, 1);
        }
        else if (e.getSource().equals(display.jButton_LeftUtilityEnemy1)){
            this.viewUtilitiesLeft(display.jUtilityCard_Enemy1, 1);
        }       
        else if (e.getSource().equals(display.jButton_RightStationEnemy1)){
            this.viewStationsRight(display.jStationCard_Enemy1, 1);
        }
        else if (e.getSource().equals(display.jButton_LeftStationEnemy1)){
            this.viewStationsLeft(display.jStationCard_Enemy1, 1);
        }
        else if (e.getSource().equals(display.jButton_RightPropertyEnemy2)){
            this.viewPropertiesRight(display.jPropertyCard_Enemy2, 2);
        }
        else if (e.getSource().equals(display.jButton_LeftPropertyEnemy2)){
            this.viewPropertiesLeft(display.jPropertyCard_Enemy2, 2);
        }
        else if (e.getSource().equals(display.jButton_RightUtilityEnemy2)){
            this.viewUtilitiesRight(display.jUtilityCard_Enemy2, 2);
        }
        else if (e.getSource().equals(display.jButton_LeftUtilityEnemy2)){
            this.viewUtilitiesLeft(display.jUtilityCard_Enemy2, 2);
        }   
        else if (e.getSource().equals(display.jButton_RightStationEnemy2)){
            this.viewStationsRight(display.jStationCard_Enemy2, 2);
        }
        else if (e.getSource().equals(display.jButton_LeftStationEnemy2)){
            this.viewStationsLeft(display.jStationCard_Enemy2, 2);
        }
        else if (e.getSource().equals(display.jButton_RightPropertyEnemy3)){
            this.viewPropertiesRight(display.jPropertyCard_Enemy3, 3);
        }
        else if (e.getSource().equals(display.jButton_LeftPropertyEnemy3)){
            this.viewPropertiesLeft(display.jPropertyCard_Enemy3, 3);
        }
        else if (e.getSource().equals(display.jButton_RightUtilityEnemy3)){
            this.viewUtilitiesRight(display.jUtilityCard_Enemy3, 3);
        }
        else if (e.getSource().equals(display.jButton_LeftUtilityEnemy3)){
            this.viewUtilitiesLeft(display.jUtilityCard_Enemy3, 3);
        }   
        else if (e.getSource().equals(display.jButton_RightStationEnemy3)){
            this.viewStationsRight(display.jStationCard_Enemy3, 3);
        }
        else if (e.getSource().equals(display.jButton_LeftStationEnemy3)){
            this.viewStationsLeft(display.jStationCard_Enemy3, 3);
        }
        else if (e.getSource().equals(display.jButton_RightPropertyEnemy4)){
            this.viewPropertiesRight(display.jPropertyCard_Enemy4, 4);
        }
        else if (e.getSource().equals(display.jButton_LeftPropertyEnemy4)){
            this.viewPropertiesLeft(display.jPropertyCard_Enemy4, 4);
        }
        else if (e.getSource().equals(display.jButton_RightUtilityEnemy4)){
            this.viewUtilitiesRight(display.jUtilityCard_Enemy4, 4);
        }
        else if (e.getSource().equals(display.jButton_LeftUtilityEnemy4)){
            this.viewUtilitiesLeft(display.jUtilityCard_Enemy4, 4);
        }   
        else if (e.getSource().equals(display.jButton_RightStationEnemy4)){
            this.viewStationsRight(display.jStationCard_Enemy4, 4);
        }
        else if (e.getSource().equals(display.jButton_LeftStationEnemy4)){
            this.viewStationsLeft(display.jStationCard_Enemy4, 4);
        }
        else if (e.getSource().equals(display.jButton_RightPropertyEnemy5)){
            this.viewPropertiesRight(display.jPropertyCard_Enemy5, 5);
        }
        else if (e.getSource().equals(display.jButton_LeftPropertyEnemy5)){
            this.viewPropertiesLeft(display.jPropertyCard_Enemy5, 5);
        }
        else if (e.getSource().equals(display.jButton_RightUtilityEnemy5)){
            this.viewUtilitiesRight(display.jUtilityCard_Enemy5, 5);
        }
        else if (e.getSource().equals(display.jButton_LeftUtilityEnemy5)){
            this.viewUtilitiesLeft(display.jUtilityCard_Enemy5, 5);
        }   
        else if (e.getSource().equals(display.jButton_RightStationEnemy5)){
            this.viewStationsRight(display.jStationCard_Enemy5, 5);
        }
        else if (e.getSource().equals(display.jButton_LeftStationEnemy5)){
            this.viewStationsLeft(display.jStationCard_Enemy5, 5);
        }
        else if (e.getSource().equals(display.jButton_RightPropertyEnemy6)){
            this.viewPropertiesRight(display.jPropertyCard_Enemy6, 6);
        }
        else if (e.getSource().equals(display.jButton_LeftPropertyEnemy6)){
            this.viewPropertiesLeft(display.jPropertyCard_Enemy6, 6);
        }
        else if (e.getSource().equals(display.jButton_RightUtilityEnemy6)){
            this.viewUtilitiesRight(display.jUtilityCard_Enemy6, 6);
        }
        else if (e.getSource().equals(display.jButton_LeftUtilityEnemy6)){
            this.viewUtilitiesLeft(display.jUtilityCard_Enemy6, 6);
        }   
        else if (e.getSource().equals(display.jButton_RightStationEnemy6)){
            this.viewStationsRight(display.jStationCard_Enemy6, 6);
        }
        else if (e.getSource().equals(display.jButton_LeftStationEnemy6)){
            this.viewStationsLeft(display.jStationCard_Enemy6, 6);
        }
        // ACTION TILES
        else if (e.getSource().equals(display.jButton_Build)){
            if (client.clientThread.player.isTurn()){
                buildController.showDisplay(currentCardSelected);
            }
        }
        
        else if (e.getSource().equals(display.jButton_EndTurn)){
            if (client.clientThread.player.isTurn()){
                client.clientThread.player.setTurn(false);
                client.clientThread.sendPackage(new Turn());
                this.deactivateButtons();
            }
        }
        
        else if (e.getSource().equals(display.jButton_Trade)){
            if (client.clientThread.player.isTurn()){
                tradeController.showDisplay();
            }
        }
               
        else if (e.getSource().equals(display.jButton_Mortgage)){
            if (client.clientThread.player.isTurn()){
                mortgageController.showDisplay(currentCardSelected);
            }
        }
        
        else if (e.getSource().equals(display.jButton_RollDice)){
            if (client.clientThread.player.isTurn()){
                client.clientThread.sendPackage(new Move(client.clientThread.player.getId(), client.clientThread.player.getPosition()));
                display.jButton_RollDice.setEnabled(false); 
            }
        }
        else if (e.getSource().equals(display.jButton_Purchase)){
            if (client.clientThread.player.isTurn()){
                IPurchasable card = (IPurchasable) Game.getCardAt(client.board, client.clientThread.player.getPosition());
                if (client.clientThread.player.purchase(card.getPrice())){
                    client.clientThread.sendPackage(new Business(client.clientThread.player.getId(), client.clientThread.player.getPosition(), BusinessType.Purchase));
                    display.jButton_Purchase.setEnabled(false);
                    this.updateMoney();
                }
                else
                    this.addActivity("Not enough money!");
            }
        }   
        else if (e.getSource().equals(display.jButton_Pay)){
            if (client.clientThread.player.isTurn()){
                Pair<Integer, Integer> debt = client.clientThread.player.payDebt();
                if (debt != null){
                    client.clientThread.sendPackage(new Transaction(debt.getKey(), debt.getValue(), "You recieved a payment from " + client.clientThread.player.getId()));
                    this.hidePayBox();
                    this.updateMoney();
                }
                else
                    this.addActivity("Not enough money!");
            }
        }
        else if (e.getSource().equals(display.jButton_Bankrupt)){
            if (client.clientThread.player.isTurn()){
                //end player game and turn
            }
        }
    }
    
    public void addMessage(String name, String message){
        display.jTextArea_Chat.append(name + ": " + message + "\n");
    }
    
    public void addActivity(String activity){
        display.jTextArea_Activity.append(activity + "\n");
    }
    
    public void welcomePlayer(int id){
        display.setTitle("Player " + id);
        display.jTextArea_Chat.append(client.clientThread.player.getName() + " welcome to the chat room\n");
    }
    
    public void showEnemyTabs(int playerAmount){
        for(int i = 6; i > playerAmount; i--){
            this.display.jTabbedPane_Information.removeTabAt(i);
        } 
        this.display.jTabbedPane_Information.removeTabAt(client.clientThread.player.getId());
    }
    
    public void updateMoney(){
        display.jLabel_money.setText("$" + client.clientThread.player.getMoney());
    }
    
    public void setLeftDice(int n)
    {
        if(n == 1){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice1.png"); 
            display.jLabel_LeftDice.setIcon(icon);
        }
        else if(n == 2){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice2.png"); 
            display.jLabel_LeftDice.setIcon(icon);
        }
        else if(n == 3){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice3.png"); 
            display.jLabel_LeftDice.setIcon(icon);
        }
        else if(n == 4){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice4.png"); 
            display.jLabel_LeftDice.setIcon(icon);
        }
        else if(n == 5){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice5.png"); 
            display.jLabel_LeftDice.setIcon(icon);
        }
        else if(n == 6){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice6.png"); 
            display.jLabel_LeftDice.setIcon(icon);
        }
    }
    
    public void setRightDice(int n)
    {
        if(n == 1){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice1.png"); 
            display.jLabel_RightDice.setIcon(icon);
        }
        else if(n == 2){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice2.png"); 
            display.jLabel_RightDice.setIcon(icon);
        }
        else if(n == 3){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice3.png"); 
            display.jLabel_RightDice.setIcon(icon);
        }
        else if(n == 4){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice4.png"); 
            display.jLabel_RightDice.setIcon(icon);
        }
        else if(n == 5){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice5.png"); 
            display.jLabel_RightDice.setIcon(icon);
        }
        else if(n == 6){
            ImageIcon icon = new ImageIcon("Assets/Dice/Dice6.png"); 
            display.jLabel_RightDice.setIcon(icon);
        }
    }
    
    
    public void viewPropertiesRight(JPropertyCard panel, int playerId){
        if (playerId == 0){
            if (!client.clientThread.player.properties.isEmpty()){
                if (personIndexPropertyCards[playerId]+1 < client.clientThread.player.properties.size()){
                    this.personIndexPropertyCards[playerId]++;
                    if (client.clientThread.player.properties.get(personIndexPropertyCards[playerId]).getType() == PathCardType.Property){
                        this.setPropertyText(panel, (Property) this.client.clientThread.player.properties.get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewPropertiesRight(panel, playerId);
                }
                else
                    return;
            }
        }
        else{
            if (client.clientThread.player.enemyProperties.get(playerId) != null){
                if (personIndexPropertyCards[playerId]+1 < client.clientThread.player.enemyProperties.get(playerId).size()){
                    this.personIndexPropertyCards[playerId]++;
                    if (client.clientThread.player.enemyProperties.get(playerId).get(personIndexPropertyCards[playerId]).getType() == PathCardType.Property){
                        this.setPropertyText(panel, (Property) this.client.clientThread.player.enemyProperties.get(playerId).get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewPropertiesRight(panel, playerId);
                }
                else
                    return; 
            }
        }
    }
    
    public void viewPropertiesLeft(JPropertyCard panel, int playerId){
        if (playerId == 0){
            if (!client.clientThread.player.properties.isEmpty()){
                if (personIndexPropertyCards[playerId]-1 >= 0){
                    this.personIndexPropertyCards[playerId]--;
                    if (client.clientThread.player.properties.get(personIndexPropertyCards[playerId]).getType() == PathCardType.Property){
                        this.setPropertyText(panel, (Property) this.client.clientThread.player.properties.get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewPropertiesLeft(panel, playerId);
                }
                else
                    return;
            }
        }
        else{
            if (client.clientThread.player.enemyProperties.get(playerId) != null){
                if (personIndexPropertyCards[playerId]-1 >= 0){
                    this.personIndexPropertyCards[playerId]--;
                    if (client.clientThread.player.enemyProperties.get(playerId).get(personIndexPropertyCards[playerId]).getType() == PathCardType.Property){
                        this.setPropertyText(panel, (Property) this.client.clientThread.player.enemyProperties.get(playerId).get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewPropertiesLeft(panel, playerId);
                }
                else
                    return; 
            }
        }
    }
    
        public void viewStationsRight(JStationCard panel, int playerId){
        if (playerId == 0){
            if (!client.clientThread.player.properties.isEmpty()){
                if (personIndexPropertyCards[playerId]+1 < client.clientThread.player.properties.size()){
                    this.personIndexPropertyCards[playerId]++;
                    if (client.clientThread.player.properties.get(personIndexPropertyCards[playerId]).getType() == PathCardType.Station){
                        this.setStationText(panel, (Station) this.client.clientThread.player.properties.get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewStationsRight(panel, playerId);
                }
                else
                    return;
            }
        } 
    }
    
    public void viewStationsLeft(JStationCard panel, int playerId){
        if (playerId == 0){
            if (!client.clientThread.player.properties.isEmpty()){
                if (personIndexPropertyCards[playerId]-1 >= 0){
                    this.personIndexPropertyCards[playerId]--;
                    if (client.clientThread.player.properties.get(personIndexPropertyCards[playerId]).getType() == PathCardType.Station){
                        this.setStationText(panel, (Station) this.client.clientThread.player.properties.get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewStationsLeft(panel, playerId);
                }
                else
                    return;
            }
        } 
    }
    
    public void viewUtilitiesRight(JUtilityCard panel, int playerId){
        if (playerId == 0){
            if (!client.clientThread.player.properties.isEmpty()){
                if (personIndexPropertyCards[playerId]+1 < client.clientThread.player.properties.size()){
                    this.personIndexPropertyCards[playerId]++;
                    if (client.clientThread.player.properties.get(personIndexPropertyCards[playerId]).getType() == PathCardType.Utility){
                        this.setUtilityText(panel, (Utility) this.client.clientThread.player.properties.get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewUtilitiesRight(panel, playerId);
                }
                else
                    return;
            }
        } 
    }
    
    public void viewUtilitiesLeft(JUtilityCard panel, int playerId){
        if (playerId == 0){
            if (!client.clientThread.player.properties.isEmpty()){
                if (personIndexPropertyCards[playerId]-1 >= 0){
                    this.personIndexPropertyCards[playerId]--;
                    if (client.clientThread.player.properties.get(personIndexPropertyCards[playerId]).getType() == PathCardType.Utility){
                        this.setUtilityText(panel, (Utility) this.client.clientThread.player.properties.get(personIndexPropertyCards[playerId]));
                        return;
                    }
                    else
                        this.viewUtilitiesLeft(panel, playerId);
                }
                else
                    return;
            }
        } 
    }
    
    public void drawWildCard(DrawType drawType, IWildCard card){
        if (drawType == DrawType.Chance)
            this.setChanceText(display.jChanceCard, card);
        else
            this.setCommunityText(display.jCommunityCard, card);
    }
    
    public void deactivateButtons(){
        display.jButton_Purchase.setEnabled(false);
        display.jButton_RollDice.setEnabled(false);
        display.jButton_EndTurn.setEnabled(false);
        display.jButton_Build.setEnabled(false);
        display.jButton_Mortgage.setEnabled(false);
        display.jButton_Trade.setEnabled(false);
        display.jButton_Pay.setEnabled(false);
        display.jButton_Bankrupt.setEnabled(false);
        display.jButton_Pay.hide();
        display.jButton_Bankrupt.hide();
    }
    
    public void activateButtons(){
        display.jButton_RollDice.setEnabled(true);
        display.jButton_EndTurn.setEnabled(true);
        display.jButton_Build.setEnabled(true);
        display.jButton_Mortgage.setEnabled(true);
        display.jButton_Trade.setEnabled(true);
    }
    
    public void activateButtonPurchase(){
        display.jButton_Purchase.setEnabled(true);
    }
    
    public void showPayBox(int amount, String msg){
        display.jLabel_PayMsg.setText(msg);
        display.jButton_Pay.setEnabled(true);
        display.jButton_Bankrupt.setEnabled(true);
        display.jButton_EndTurn.setEnabled(false);
        display.jButton_Pay.show();
        display.jButton_Bankrupt.show();        
    }
    
    public void hidePayBox(){
        display.jLabel_PayMsg.setText("");
        display.jButton_Pay.setEnabled(false);
        display.jButton_Bankrupt.setEnabled(false);
        display.jButton_EndTurn.setEnabled(true);
        display.jButton_Pay.hide();
        display.jButton_Bankrupt.hide();        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void drawHousesBoard(){
        for(int i = 0; i < this.client.clientThread.player.properties.size(); i++){
            if (this.client.clientThread.player.properties.get(i).getType() == Property)
                if(((Property)this.client.clientThread.player.properties.get(i)).houses > 0){
                    colorHousesBoard((Property)this.client.clientThread.player.properties.get(i));
                }
        }
        for(ArrayList<IPurchasable> cards : client.clientThread.player.enemyProperties.values()) {
            for (IPurchasable card : cards) { 
                if(card.getType() == Property){
                    if (((Property)card).houses > 0)
                        colorHousesBoard((Property)card);
                }
            }
        }
    }

    private void colorHousesBoard(Property property) {
        if(null != property.name){
            switch (property.name) {
                case "mediterranean avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses1);
                    break;
                case "baltic avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses2);
                    break;
                case "oriental avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses3);
                    break;
                case "vermont avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses4);
                    break;
                case "connecticut avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses5);
                    break;
                case "st. charles place":
                    colorHousesBoard_Aux(property,this.display.jHousesV1);
                    break;
                case "states avenue":
                    colorHousesBoard_Aux(property,this.display.jHousesV2);
                    break;
                case "virginia avenue":
                    colorHousesBoard_Aux(property,this.display.jHousesV3);
                    break;
                case "st. james place":
                    colorHousesBoard_Aux(property,this.display.jHousesV4);
                    break;
                case "tennessee avenue":
                    colorHousesBoard_Aux(property,this.display.jHousesV5);
                    break;
                case "new york avenue":
                    colorHousesBoard_Aux(property,this.display.jHousesV6);
                    break;
                case "kentucky avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses6);
                    break;
                case "indiana avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses7);
                    break;
                case "illinois avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses8);
                    break;
                case "atlantic avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses9);
                    break;
                case "ventnor avenue":
                    colorHousesBoard_Aux(property,this.display.jHouses10);
                    break;
                case "marvin gardens":
                    colorHousesBoard_Aux(property,this.display.jHouses11);
                    break;
                case "pacific avenue":
                    colorHousesBoard_Aux(property,this.display.jHousesV7);
                    break;
                case "north carolina avenue": 
                    colorHousesBoard_Aux(property,this.display.jHousesV8);
                    break;
                case "pennsylvania avenue":
                    colorHousesBoard_Aux(property,this.display.jHousesV9);
                    break;
                case "park place":
                    colorHousesBoard_Aux(property,this.display.jHousesV10);
                    break;
                case "boardwalk":
                    colorHousesBoard_Aux(property,this.display.jHousesV11);
                    break;
                default:
                    break;
            }
        }
    }

    private void colorHousesBoard_Aux(Property property, JHouses jHouses) {
        if(property.houses > 0)
            jHouses.jLabel_House1.setBackground(Color.GREEN);
        if(property.houses > 1)
            jHouses.jLabel_House2.setBackground(Color.GREEN);
        if(property.houses > 2)
            jHouses.jLabel_House3.setBackground(Color.GREEN);
        if(property.houses > 3)
            jHouses.jLabel_House4.setBackground(Color.GREEN);
        if(property.hotel == true){
            jHouses.jLabel_House1.setBackground(Color.RED);
            jHouses.jLabel_House2.setBackground(Color.RED);
            jHouses.jLabel_House3.setBackground(Color.RED);
            jHouses.jLabel_House4.setBackground(Color.RED);
        }
    }
    
    private void colorHousesBoard_Aux(Property property, JHousesV jHouses) {
        if(property.houses > 0)
            jHouses.jLabel_House1.setBackground(Color.GREEN);
        if(property.houses > 1)
            jHouses.jLabel_House2.setBackground(Color.GREEN);
        if(property.houses > 2)
            jHouses.jLabel_House3.setBackground(Color.GREEN);
        if(property.houses > 3)
            jHouses.jLabel_House4.setBackground(Color.GREEN);
        if(property.hotel == true){
            jHouses.jLabel_House1.setBackground(Color.RED);
            jHouses.jLabel_House2.setBackground(Color.RED);
            jHouses.jLabel_House3.setBackground(Color.RED);
            jHouses.jLabel_House4.setBackground(Color.RED);
        }
    }
    
    public void setImageToButton(String imageStr, int position)
    {
       try {
        BufferedImage image = ImageIO.read(new File(imageStr));
            JButton button = getButton(position);
            button.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
          System.out.println("Failed to load icon");
        }
    }
    
    public JButton getButton(int position)
    {
        switch(position){
            case 0:
                return this.display.jButton_Go;
            case 1:
                return this.display.jButtom_Slots0;
            case 2:
                return this.display.jButtom_Slots1;
            case 3:
                return this.display.jButtom_Slots2; 
            case 4:
                return this.display.jButtom_Slots3;
            case 5:
                return this.display.jButtom_Slots4;
            case 6:
                return this.display.jButtom_Slots5;
            case 7:
                return this.display.jButtom_Slots6;
            case 8:
                return this.display.jButtom_Slots7;
            case 9:
                return this.display.jButtom_Slots8;    
            case 10:
                return this.display.jButton_Jail;
            case 11:
                return this.display.jButtom_Slots9;    
            case 12:
                return this.display.jButtom_Slots10;
            case 13:
                return this.display.jButtom_Slots11;    
            case 14:
                return this.display.jButtom_Slots12;    
            case 15:
                return this.display.jButtom_Slots13;    
            case 16:
                return this.display.jButtom_Slots14;    
            case 17:
                return this.display.jButtom_Slot15;    
            case 18:
                return this.display.jButtom_Slot16;  
            case 19:
                return this.display.jButtom_Slot17;
            case 20:
                return this.display.jButton_Parking;
            case 21:
                return this.display.jButtom_Slots18;
            case 22:
                return this.display.jButtom_Slots19; 
            case 23:
                return this.display.jButtom_Slots20;
            case 24:
                return this.display.jButtom_Slot21;
            case 25:
                return this.display.jButtom_Slot22;
            case 26:
                return this.display.jButtom_Slot23;
            case 27:
                return this.display.jButtom_Slot24;
            case 28:
                return this.display.jButtom_Slot25;    
            case 29:
                return this.display.jButtom_Slot26;
            case 30:
                return this.display.jButton_ToJail;    
            case 31:
                return this.display.jButtom_Slot27;
            case 32:
                return this.display.jButtom_Slots28;    
            case 33:
                return this.display.jButtom_Slots29;    
            case 34:
                return this.display.jButtom_Slots30;    
            case 35:
                return this.display.jButtom_Slot31;    
            case 36:
                return this.display.jButtom_Slot32;    
            case 37:
                return this.display.jButtom_Slot33;
            case 38:
                return this.display.jButtom_Slot34;    
            case 39:
                return this.display.jButtom_Slot35;      
        }
        return null;       
    }
    
    public void resetButtons()
    {
             this.display.jButton_Go.setIcon(null);
             this.display.jButtom_Slots0.setIcon(null);
             this.display.jButtom_Slots1.setIcon(null);
             this.display.jButtom_Slots2.setIcon(null); 
             this.display.jButtom_Slots3.setIcon(null);
             this.display.jButtom_Slots4.setIcon(null);
             this.display.jButtom_Slots5.setIcon(null);
             this.display.jButtom_Slots6.setIcon(null);
             this.display.jButtom_Slots7.setIcon(null);
             this.display.jButtom_Slots8.setIcon(null);    
             this.display.jButton_Jail.setIcon(null);
             this.display.jButtom_Slots9.setIcon(null);    
             this.display.jButtom_Slots10.setIcon(null);
             this.display.jButtom_Slots11.setIcon(null);    
             this.display.jButtom_Slots12.setIcon(null);    
             this.display.jButtom_Slots13.setIcon(null);    
             this.display.jButtom_Slots14.setIcon(null);    
             this.display.jButtom_Slot15.setIcon(null);    
             this.display.jButtom_Slot16.setIcon(null);  
             this.display.jButtom_Slot17.setIcon(null);
             this.display.jButton_Parking.setIcon(null);
             this.display.jButtom_Slots18.setIcon(null);
             this.display.jButtom_Slots19.setIcon(null); 
             this.display.jButtom_Slots20.setIcon(null);
             this.display.jButtom_Slot21.setIcon(null);
             this.display.jButtom_Slot22.setIcon(null);
             this.display.jButtom_Slot23.setIcon(null);
             this.display.jButtom_Slot24.setIcon(null);
             this.display.jButtom_Slot25.setIcon(null);    
             this.display.jButtom_Slot26.setIcon(null);
             this.display.jButton_ToJail.setIcon(null);    
             this.display.jButtom_Slot27.setIcon(null);
             this.display.jButtom_Slots28.setIcon(null);    
             this.display.jButtom_Slots29.setIcon(null);    
             this.display.jButtom_Slots30.setIcon(null);    
             this.display.jButtom_Slot31.setIcon(null);    
             this.display.jButtom_Slot32.setIcon(null);    
             this.display.jButtom_Slot33.setIcon(null);
             this.display.jButtom_Slot34.setIcon(null);    
             this.display.jButtom_Slot35.setIcon(null);            
    }
    
    public void drawPlayerPositions()
    {
        this.resetButtons();
        for(Integer playerId : client.clientThread.player.enemyPositions.keySet()) {
            System.out.println(playerId + " " + client.clientThread.player.enemyTokens.get(playerId));
            setPlayerIcon(this.client.clientThread.player.enemyPositions.get(playerId), this.client.clientThread.player.enemyTokens.get(playerId));
        }
        setPlayerIcon(this.client.clientThread.player.getPosition(), this.client.clientThread.player.getToken());
    }
    
    public void setPlayerIcon(int position, Token token)
    {
        switch(token){
            case Boat:
                setImageToButton("Assets/Tokens/boat.png", position);
                break;
            case Boot:
                setImageToButton("Assets/Tokens/boot.png", position);
                break;
            case Cannon:
                setImageToButton("Assets/Tokens/cannon.png", position);
                break;                
            case Car:
                setImageToButton("Assets/Tokens/car.png", position);
                break;    
            case Carriage:
                setImageToButton("Assets/Tokens/carreta.png", position);
                break;
            case Thimbal:
                setImageToButton("Assets/Tokens/dedal.png", position);
                break;
            case Dog:
                setImageToButton("Assets/Tokens/dog.png", position);
                break;
            case Guitar:
                setImageToButton("Assets/Tokens/guitar.png", position);
                break;
            case Hat:
                setImageToButton("Assets/Tokens/hat.png", position);
                break;
            case Horse:
                setImageToButton("Assets/Tokens/horse.png", position);
                break;
            case Bag:
                setImageToButton("Assets/Tokens/moneybag.png", position);
                break;
            case Iron:
                setImageToButton("Assets/Tokens/palcha.png", position);
                break;    
        }
    }
   
}


