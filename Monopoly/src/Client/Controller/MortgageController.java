
package Client.Controller;

import Client.Model.Client;
import Client.View.JPropertyCard;
import Client.View.JStationCard;
import Client.View.JUtilityCard;
import Client.View.MortgageDisplay;
import Game.PathCards.IPurchasable;
import Game.PathCards.Property;
import Game.PathCards.Station;
import Game.PathCards.Utility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class MortgageController implements ActionListener{
    
    private MortgageDisplay display;
    private ClientController controller;
    private Client client;
    private IPurchasable selectedCard;
    
    public MortgageController(MortgageDisplay display, ClientController controller, Client client) {
        this.display = display;
        this.controller = controller;
        this.client = client;
        this.innit();
    }
    
    private void innit(){
        display.jButton_Confirm.addActionListener(this);
        display.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display.setLocationRelativeTo(null);
    }
    
    public void showDisplay(IPurchasable selectedCard){
        this.selectedCard = selectedCard;
        this.drawCard();
        display.setVisible(true);
    }
    
    public void drawCard(){
        if (selectedCard != null){
            switch(selectedCard.getType()){
                case Property:
                    this.setPropertyText(display.jPropertyCard_Mortgage, (Property)selectedCard);
                    break;
                case Station:
                    this.setStationText(display.jStationCard_Mortgage, (Station)selectedCard);
                    break;
                case Utility:
                    this.setUtilityText(display.jUtilityCard_Mortgage, (Utility)selectedCard);
                    break;                    
            }
            display.jButton_Confirm.setEnabled(true);
        }
        else
            display.jButton_Confirm.setEnabled(false);
    }
    
    public void setPropertyText(JPropertyCard card_GUI, Property card_Logic)
    {
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
        card_GUI.jLabel_Name.setText(card_Logic.name);
    }
    
    public void setUtilityText(JUtilityCard card_GUI, Utility card_Logic)
    {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Confirm)){
            
        }
    }
}
