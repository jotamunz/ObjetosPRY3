
package Game.PathCards;

public class CardDraw implements IPathCard{
    
    public enum DrawType {Chance, Community}
    
    public String name;
    public DrawType type;

    public CardDraw(DrawType type) {
        this.name = type.toString();
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public PathCardType getType() {
        return PathCardType.CardDraw;
    }
    
    
    
}
