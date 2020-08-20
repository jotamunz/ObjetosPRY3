
package Game.PathCards;

import java.io.Serializable;

public interface IPathCard extends Serializable{
    
    public PathCardType getType();
    public String getName();
}
