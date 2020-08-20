
package Game.WildCards;

import Game.PathCards.PathCardType;

public class AdvanceToNearest implements IWildCard{
    
    public String description;
    public PathCardType destination;

    public AdvanceToNearest(String description, PathCardType destination) {
        this.description = description;
        this.destination = destination;
    }
    
    @Override
    public String getDesc() {
        return description;
    }

    @Override
    public WildCardType getType() {
        return WildCardType.AdvanceToNearest;
    }
    
}
