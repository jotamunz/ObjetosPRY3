
package Game.WildCards;

public class GoBack implements IWildCard{
    
    public String description;
    public int spaces;

    public GoBack(String description, int spaces) {
        this.description = description;
        this.spaces = spaces;
    }
    
    @Override
    public String getDesc() {
        return description;
    }
    
    @Override
    public WildCardType getType() {
        return WildCardType.GoBack;
    }
    
}
