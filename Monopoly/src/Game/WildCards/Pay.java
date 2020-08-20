
package Game.WildCards;

public class Pay implements IWildCard{
    
    public enum Receptor {Bank , All}
    
    public String description;
    public Receptor receptor;
    public int amount;

    public Pay(String description, Receptor receptor, int amount) {
        this.description = description;
        this.receptor = receptor;
        this.amount = amount;
    }
    
    @Override
    public String getDesc() {
        return description;
    }

    @Override
    public WildCardType getType() {
        return WildCardType.Pay;
    }
    
}
