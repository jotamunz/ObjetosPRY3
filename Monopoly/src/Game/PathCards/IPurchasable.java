
package Game.PathCards;

import java.io.Serializable;

public interface IPurchasable extends Serializable{
    
    public int getPrice();
    public void buy(int ownerId);
    public PathCardType getType();
}
