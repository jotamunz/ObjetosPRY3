
package Game.WildCards;

public class Collect implements IWildCard{

    public enum Giver {Bank , All}
    
    public String description;
    public Giver giver;
    public int amount;

    public Collect(String description, Giver giver, int amount) {
        this.description = description;
        this.giver = giver;
        this.amount = amount;
    }
    
    @Override
    public String getDesc() {
        return description;
    }
    
    @Override
    public WildCardType getType() {
        return WildCardType.Collect;
    }
    
}
