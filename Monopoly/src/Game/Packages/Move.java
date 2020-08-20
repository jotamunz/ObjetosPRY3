
package Game.Packages;

import Game.PathCards.CardDraw.DrawType;
import Game.PathCards.PathCardType;
import Game.WildCards.IWildCard;
import javafx.util.Pair;

public class Move implements IPackage{
    
    private int playerId;
    private int position;
    private Pair<Integer, Integer> rollAmount;
    private String activity;
    private PathCardType cardType;
    private boolean isPurchasable;
    
    private IWildCard wildCard;
    private DrawType drawType;

    public Move(int playerId, int position, Pair<Integer, Integer> rollAmount, String activity, PathCardType cardType, boolean isPurchasable) {
        this.playerId = playerId;
        this.position = position;
        this.rollAmount = rollAmount;
        this.activity = activity;
        this.cardType = cardType;
        this.isPurchasable = isPurchasable;
    }
    
    public Move(int playerId, int position, Pair<Integer, Integer> rollAmount, String activity, PathCardType cardType, boolean isPurchasable, IWildCard wildCard, DrawType drawType) {
        this.playerId = playerId;
        this.position = position;
        this.rollAmount = rollAmount;
        this.activity = activity;
        this.cardType = cardType;
        this.isPurchasable = isPurchasable;
        this.wildCard = wildCard;
        this.drawType = drawType;
    }
    
    public Move(int playerId, int position){
        this.playerId = playerId;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
    
    public int getPlayerId(){
        return playerId;
    }

    public Pair<Integer, Integer> getRollAmount() {
        return rollAmount;
    }

    public String getActivity() {
        return activity;
    }
    
    public PathCardType getCardType(){
        return cardType;
    }
    
    public boolean isPurchasable(){
        return isPurchasable;
    }
    
    public IWildCard getWildCard(){
        return wildCard;
    }
   
    public DrawType getDrawTye(){
        return drawType;
    }
    
    @Override
    public PackageEnum getType() {
        return PackageEnum.Move;
    }
    
}
