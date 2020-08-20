
package Game.WildCards;

public class JailFree implements IWildCard{
    
    public String description;

    public JailFree(String description) {
        this.description = description;
    }
    
    @Override
    public String getDesc() {
        return description;
    }
    
    @Override
    public WildCardType getType() {
        return WildCardType.JailFree;
    } 
    
    
}
