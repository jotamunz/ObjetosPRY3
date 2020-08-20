
package Game.Packages;

public class Transaction implements IPackage{
    
    private int recieverId;
    private int money;
    private String description;

    public Transaction(int recieverId, int money, String description) {
        this.recieverId = recieverId;
        this.money = money;
        this.description = description;
    }
    
    public int getRecieverId(){
        return recieverId;
    }

    public int getMoney() {
        return money;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public PackageEnum getType() {
        return PackageEnum.Transaction;
    }
    
}
