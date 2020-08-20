
package Client.Controller;

import Client.Model.Client;
import Client.View.TradeDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class TradeController implements ActionListener{
    
    private TradeDisplay display;
    private ClientController controller;
    private Client client;
    
    public TradeController(TradeDisplay display, ClientController controller, Client client) {
        this.display = display;
        this.controller = controller;
        this.client = client;
        this.innit();
    }
    
    private void innit(){
        display.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display.setLocationRelativeTo(null);
    }
    
    public void showDisplay(){
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}