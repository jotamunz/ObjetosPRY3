
package Game;

import Game.PathCards.Action;
import Game.PathCards.Action.ActionType;
import Game.PathCards.IPathCard;
import Game.PathCards.PathCardType;
import Game.PathCards.*;
import Game.PathCards.CardDraw.DrawType;
import Game.WildCards.*;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardGenerator { //AGREGAR EL SHUFFLE
    
    public static Board generateBoard() {  
        Board board = new Board();
        board.board[0]  = (IPathCard) new Action("Go",ActionType.Go);
        board.board[10] = (IPathCard) new Action("Jail",ActionType.Jail);
        board.board[20] = (IPathCard) new Action("Free Parking",ActionType.FreeParking);
        board.board[30] = (IPathCard) new Action("To Jail",ActionType.ToJail);
        board.board[5]  = (IPathCard) new Station("Reading Railroad",200,25,100);
        board.board[15] = (IPathCard) new Station("Pennsylvania Railroad",200,25,100);
        board.board[25] = (IPathCard) new Station("B.& O. Railroad",200,25,100);
        board.board[35] = (IPathCard) new Station("Short Line",200,25,100);
        board.board[12] = (IPathCard) new Utility("Electricity",150,75);
        board.board[28] = (IPathCard) new Utility("Water",150,75);
        board.board[4]  = (IPathCard) new Tax("Tax over income",200);
        board.board[38] = (IPathCard) new Tax("Tax over luxury posessions",75);
        board.board[7]  = (IPathCard) new CardDraw(DrawType.Chance);
        board.board[22] = (IPathCard) new CardDraw(DrawType.Chance);
        board.board[36] = (IPathCard) new CardDraw(DrawType.Chance);       
        board.board[2]  = (IPathCard) new CardDraw(DrawType.Community);
        board.board[17] = (IPathCard) new CardDraw(DrawType.Community);
        board.board[33] = (IPathCard) new CardDraw(DrawType.Community);
        generateWildCards(board);  
        generateProperties("Assets/Properties.txt", board);
        return board;
    }
    
    public static void generateProperties(String fileName, Board board) {

        Color color;
        String name;
        int price;
        int buildPrice;
        int mortgage;
        int monopolySize;
        
        try {
            FileInputStream file = new FileInputStream(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                int[] rent = new int [6];
                String line[] = scanner.nextLine().toLowerCase().split("\t", 0);
                name = line[0];
                color = Color.decode(line[12]);
                price = Integer.parseInt(line[1]);
                buildPrice = Integer.parseInt(line[2]);
                rent[0] = Integer.parseInt(line[3]);
                rent[1] = Integer.parseInt(line[4]);
                rent[2] = Integer.parseInt(line[5]);
                rent[3] = Integer.parseInt(line[6]);
                rent[4] = Integer.parseInt(line[7]);
                rent[5] = Integer.parseInt(line[8]);
                mortgage = Integer.parseInt(line[9]);
                monopolySize = Integer.parseInt(line[10]);
                board.board[Integer.parseInt(line[11])] = new Property(name, color, price, buildPrice, rent , mortgage, monopolySize);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BoardGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void generateWildCards(Board board) {
    
        //CHANCE DECK
        //Jail
        board.chanceDeck[8] = new Advance("Go directly to jail", "To Jail", false);
        //Simple Advance
        board.chanceDeck[13] = new Advance("Advance to GO!","Go", true);
        board.chanceDeck[1] = new Advance("Advance to St. Charles Place","St. Charles Place", true);
        board.chanceDeck[2] = new Advance("Take a trip to Reading Railroad","Reading Railroad", true);
        board.chanceDeck[3] = new Advance("Advance to Boardwalk","Boardwalk", true);
        board.chanceDeck[4] = new Advance("Advance to Illinois Avenue","Illinois Avenue", true);
        //Advance to nearest
        board.chanceDeck[5] = new AdvanceToNearest("Advance to nearest utility", PathCardType.Utility);
        board.chanceDeck[6] = new AdvanceToNearest("Advance token to the nearest Railroad", PathCardType.Station);
        board.chanceDeck[7] = new AdvanceToNearest("Advance token to the nearest Railroad", PathCardType.Station);
        //Collect
        board.chanceDeck[0] = new Collect("Bank pays you dividend of $50", Collect.Giver.Bank, 50);
        board.chanceDeck[9] = new Collect("Your loan matures. Receive $150", Collect.Giver.Bank, 150);
        //Pay
        board.chanceDeck[10] = new Pay("You have been elected Chairman of the Board. Pay each player $50", Pay.Receptor.All, 50);
        board.chanceDeck[11] = new Pay("Pay poor tax of $15", Pay.Receptor.Bank, 15);
        //GoBack
        board.chanceDeck[12] = new GoBack("Go back three spaces",3);
        //Jail free
        board.chanceDeck[14] = new JailFree("Jail free card");
        //Pay Repair
        board.chanceDeck[15] = new PayRepair("Make general repairs on all your property: For each house pay $25, For each hotel pay $100", 25, 100);
        
        //COMMUNITY DECK
        //Jail 
        board.communityDeck[14] = new Advance("Go directly to jail", "To Jail", false);        
        //Simple Advance
        board.communityDeck[1] = new Advance("Advance to GO!","Go", true);
        //Collect
        board.communityDeck[12] = new Collect("Grand Opera Night.Collect $50 from every player for opening night seats.", Collect.Giver.All, 50);
        board.communityDeck[2] = new Collect("Income tax refund. Collect $20.", Collect.Giver.Bank, 20);
        board.communityDeck[3] = new Collect("Life insurance matures, collect $100.", Collect.Giver.Bank, 100);
        board.communityDeck[4] = new Collect("Receive $25 consultancy fee.", Collect.Giver.Bank, 25);
        board.communityDeck[5] = new Collect("You have won second prize in a beauty contest. Collect $10.", Collect.Giver.Bank, 10);
        board.communityDeck[6] = new Collect("Bank error in your favor. Collect $200.", Collect.Giver.Bank, 200);
        board.communityDeck[7] = new Collect("Holiday Fund matures. Receive $100.", Collect.Giver.Bank, 100);
        board.communityDeck[8] = new Collect("You inherit $100.", Collect.Giver.Bank, 100);
        board.communityDeck[9] = new Pay("From sale of stock you get $45", Pay.Receptor.Bank, 45);
        //Pay
        board.communityDeck[10] = new Pay("Hospital Fees. Pay $100.", Pay.Receptor.Bank, 100);
        board.communityDeck[11] = new Pay("School fees. Pay $150.", Pay.Receptor.Bank, 150);
        board.communityDeck[0] = new Pay("Hospital Fees. Pay $50.", Pay.Receptor.Bank, 50);     
        //Jail Free
        board.communityDeck[13] = new JailFree("Jail free card");
        //Pay Repair
        board.communityDeck[15] = new PayRepair("You are assessed for street repairs: For each house pay $40, For each hotel pay $115", 40, 115);
        
    }
}
