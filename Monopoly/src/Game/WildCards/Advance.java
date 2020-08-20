
package Game.WildCards;

public class Advance implements IWildCard{
    
    public String description;
    public String destination;
    public boolean collectGo;

    public Advance(String description, String destination, boolean collectGo) {
        this.description = description;
        this.destination = destination;
        this.collectGo = collectGo;
    }
    
    @Override
    public String getDesc() {
        return description;
    }

    @Override
    public WildCardType getType() {
        return WildCardType.Advance;
    }
    
}
