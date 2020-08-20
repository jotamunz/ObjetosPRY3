
package Game.PathCards;

public class Action implements IPathCard{
    
    public enum ActionType {Go, ToJail, Jail, FreeParking}
    
    public String name;
    public ActionType type;

    public Action(String name, ActionType type) {
        this.name = name;
        this.type = type;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public PathCardType getType() {
        return PathCardType.Action;
    }

}
