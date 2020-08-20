
package Game.WildCards;

import java.io.Serializable;

public interface IWildCard extends Serializable{
    
    public WildCardType getType();
    public String getDesc();
    
}
