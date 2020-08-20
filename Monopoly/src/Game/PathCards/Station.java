
package Game.PathCards;

public class Station implements  IPathCard, IPurchasable{
    
    public String name;
    public boolean bought;
    public int price;
    public int rent;
    public int mortgage;
    public int ownerId;

    public Station(String name, int price, int rent, int mortgage) {
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.mortgage = mortgage;
        this.bought = false;
    }
    
    public int getRent(int stationAmount){
        switch (stationAmount){
            case 2:
                return rent*2;
            case 3:
                return rent*2*2;
            case 4:
                return rent*2*2*2;
            default:
                return rent;
        }
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
    public String getName() {
        return name;
    }
    
    @Override
    public PathCardType getType() {
        return PathCardType.Station;
    }
    
}
