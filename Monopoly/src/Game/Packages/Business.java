
package Game.Packages;

import Game.PathCards.IPurchasable;

public class Business implements IPackage{
    
    public enum BusinessType{Purchase, Mortgage, Update}
    
    private int playerId;
    private int position;
    private BusinessType type;
    private IPurchasable card;

    public Business(int playerId, int position, BusinessType type) {
        this.playerId = playerId;
        this.position = position;
        this.type = type;
    }
    
    public Business(int playerId, IPurchasable card, BusinessType type) {
        this.playerId = playerId;
        this.card = card;
        this.type = type;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPosition() {
        return position;
    }

    public IPurchasable getCard() {
        return card;
    }

    public BusinessType getBusinessType() {
        return type;
    }
    
    @Override
    public PackageEnum getType() {
        return PackageEnum.Business;
    }
    
}
