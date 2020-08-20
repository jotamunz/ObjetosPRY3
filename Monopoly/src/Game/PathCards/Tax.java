
package Game.PathCards;


public class Tax implements IPathCard{

    public String name;
    public int taxAmount;

    public Tax(String name, int taxAmount) {
        this.name = name;
        this.taxAmount = taxAmount;
    }
    
    public int getTaxAmount(){
        return taxAmount;
    }    

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public PathCardType getType() {
        return PathCardType.Tax;
    }
    
}
