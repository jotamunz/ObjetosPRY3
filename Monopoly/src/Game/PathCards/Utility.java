
package Game.PathCards;

public class Utility implements IPathCard, IPurchasable{
    
    public String name;
    public boolean bought;
    public int price;
    public int mortgage;
    public int ownerId;

    public Utility(String name, int price, int mortgage) {
        this.name = name;
        this.price = price;
        this.mortgage = mortgage;
        this.bought = false;
    }
    
    @Override
    public void buy(int ownerId){
        this.bought = true;
        this.ownerId = ownerId;
    }
    
    public int getRent(int amount){
        if (amount == 1)
            return 4;
        else if (amount == 2)
            return 10;
        else return 0;
    }    
    public boolean isPurchasable() {
        return !bought;
    }
    
    @Override
    public int getPrice() {
        return price;
    }  
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public PathCardType getType() {
        return PathCardType.Utility;
    }
}
