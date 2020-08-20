
package Game;

import Game.PathCards.IPurchasable;
import Game.PathCards.PathCardType;
import Game.PathCards.Property;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

public class Player {
    
    private int id;
    private String name;
    private Token token;
    private int position;
    private boolean turn;
    private int doubles;
    public HashMap<Integer, Integer> enemyPositions;
    public HashMap<Integer, Token> enemyTokens;
    public ArrayList<IPurchasable> properties;
    public HashMap<Integer, ArrayList<IPurchasable>> enemyProperties;
    private int money;
    private Pair<Integer, Integer> debt;
    private int inJail;
    private Pair<Boolean, Boolean> jailFree;
    private boolean firstRound;
    
    public Player(int id, String name){
        this.id = id;
        this.name = name;
        this.turn = false;
        this.enemyPositions = new HashMap<>();
        this.properties = new ArrayList<>();
        this.enemyProperties = new HashMap<>();
        this.enemyTokens = new HashMap<>();
        this.inJail = 0;
        this.jailFree = new Pair(false, false);
        this.firstRound = false;
        this.doubles = 0;
        this.position = 0;
        this.money = 1500;
        this.debt = new Pair(0,0);   
    }
    
    public void setTurn(boolean turn){
        this.turn = turn;
    }
    
    public boolean isTurn(){
        return turn;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getMoney(){
        return money;
    }
    
    public void sendToJail(){
        this.inJail = 3;
    }
    
    public boolean inJail(){
        return inJail > 0;
    }
    
    public void reduceJail(){
        this.inJail -= 1;
    }
    
    public void leaveJail(){
        this.inJail = 0;
    }
    
    public int getDoubles(){
        return doubles;
    }
    
    public void resetDoubles(){
        this.doubles = 0;
    }
    
    public void addDouble(){
        this.doubles++;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public void addMoney(int money){
        this.money += money;
    }
    
    public void addDebt(Pair debt){
        this.debt = debt;
    }
    
    public void setFirstRoundComplete(){
        this.firstRound = true;
    }
    
    public boolean firstRoundIsComplete(){
        return firstRound;
    }
    
    public Pair<Integer, Integer> payDebt(){
        if (money - debt.getValue() >= 0){
            money -= debt.getValue();
            Pair<Integer, Integer> payedDebt = debt;
            this.debt = new Pair(0,0);
            return payedDebt;
        }
        return null;
    }
    
    public boolean purchase(int cost){
        if (money - cost >= 0){
            money -= cost;
            return true;
        }
        return false;
    }
    
    public boolean hasMonopoly(Color color, int monopolySize){
        int counter = 0;
        for (int i = 0; i < properties.size(); i++){
            IPurchasable card = properties.get(i);
            if (card.getType() == PathCardType.Property){
                Property property = (Property) card;
                if (property.color.getRGB() == color.getRGB())
                    counter++;
            }
        }
        return (counter == monopolySize);
    }
    
    public int utilityAmount(){
        int counter = 0;
        for (int i = 0; i < properties.size(); i++){
            IPurchasable card = properties.get(i);
            if (card.getType() == PathCardType.Utility){
                counter++;
            }
        }
        return counter;
    }
    
    public int stationAmount(){
        int counter = 0;
        for (int i = 0; i < properties.size(); i++){
            IPurchasable card = properties.get(i);
            if (card.getType() == PathCardType.Station){
                counter++;
            }
        }
        return counter;
    }
}
