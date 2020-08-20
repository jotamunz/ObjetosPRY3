
package Game.Packages;

import Game.PathCards.Property;

public class Build implements IPackage{
    
    private Property card;
    private int playerId;

    public Build(int playerId, Property card) {
        this.card = card;
        this.playerId = playerId;
    }

    public Property getCard() {
        return card;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public PackageEnum getType() {
        return PackageEnum.Build;
    }
    
}
