
package Game.Packages;

public class Turn implements IPackage{
    
    private boolean firstTurn;
    
    public Turn (boolean firstTurn){
        this.firstTurn = firstTurn;
    }
    
    public Turn(){
        this.firstTurn = false;
    }
    
    public boolean isFirstTrurn(){
        return firstTurn;
    }

    @Override
    public PackageEnum getType() {
        return PackageEnum.Turn;
    }
    
}
