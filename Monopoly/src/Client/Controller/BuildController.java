
package Client.Controller;

import Client.Model.Client;
import Client.View.BuildDisplay;
import Client.View.JPropertyCard;
import Game.Game;
import Game.Packages.Build;
import Game.PathCards.IPurchasable;
import Game.PathCards.PathCardType;
import Game.PathCards.Property;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class BuildController implements ActionListener{
    
    private BuildDisplay display;
    private ClientController controller;
    private Client client;
    private IPurchasable selectedCard;
    
    public BuildController(BuildDisplay display, ClientController controller, Client client) {
        this.display = display;
        this.controller = controller;
        this.client = client;
        this.innit();
    }
    
    private void innit(){
        display.jButton_addHouse.addActionListener(this);
        display.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display.setLocationRelativeTo(null);
    }
    
    public void showDisplay(IPurchasable selectedCard){
        this.selectedCard = selectedCard;
        this.drawCard();
        display.setVisible(true);
    }

    public void drawCard(){
        if (selectedCard != null && selectedCard.getType() == PathCardType.Property){
            this.setPropertyText(display.jPropertyCard_Build, (Property)selectedCard);
            display.jButton_addHouse.setEnabled(true);
        }
        else
            display.jButton_addHouse.setEnabled(false);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_addHouse)){
            if (client.clientThread.player.purchase(((Property)selectedCard).buildPrice)){
                client.clientThread.sendPackage(new Build(client.clientThread.player.getId(), (Property)selectedCard));
                controller.updateMoney();
            }
            else
                controller.addActivity("Not enough money!");
        }
    }
    
}
