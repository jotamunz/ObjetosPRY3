
package Game.WildCards;

public class PayRepair implements IWildCard{
    
    public String description;
    public int houseAmount;
    public int hotelAmount;

    public PayRepair(String description, int houseAmount, int hotelAmount) {
        this.description = description;
        this.houseAmount = houseAmount;
        this.hotelAmount = hotelAmount;
    }
    
    @Override
    public String getDesc() {
        return description;
    }
    
    @Override
    public WildCardType getType() {
        return WildCardType.PayRepair;
    }
    
}
