
package Game.PathCards;

import java.awt.Color;

public class Property implements IPathCard, IPurchasable{
    
    public String name;
    public boolean bought;
    public Color color;
    public int houses;
    public boolean hotel;
    public int price;
    public int buildPrice;
    public int[] rent;
    public int mortgage;
    public int monopolySize;
    public int ownerId;

    public Property(String name, Color color, int price, int buildPrice, int[] rent, int mortgage, int monopolySize) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.buildPrice = buildPrice;
        this.rent = rent;
        this.mortgage = mortgage;
        this.monopolySize = monopolySize;
        this.bought = false;
        this.hotel = false;
        this.houses = 0;
    }

    public int getRent(boolean hasMonopoly){
        if (hotel)
            return rent[5];
        else if (houses > 0)
            return rent[houses];
        else if (hasMonopoly)
            return rent[0]*2;
        else
            return rent[0];
    }
    
    @Override
    public void buy(int ownerId){
        this.bought = true;
        this.ownerId = ownerId;
    }    
    
    public boolean isPurchasable() {
        return !bought;
    }
    
    @Override
    public int getPrice() {
        return price;
    }
    
    @Override
    public PathCardType getType() {
        return PathCardType.Property;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Name: " + this.name +  "\t Price :" + this.price + "\t BuildPrice :" + this.buildPrice + "\t Color :" + this.color.toString()+ "\t Rent :"  + this.rent[0] +","+this.rent[1]+","+this.rent[2]+","+this.rent[3]+","+this.rent[4]+","+this.rent[5]+ "\t Morgage :" + this.mortgage + "\t Monopoly Size :" + this.monopolySize;
    }
    
}
