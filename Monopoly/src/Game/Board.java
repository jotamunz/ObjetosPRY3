
package Game;

import Game.PathCards.IPathCard;
import Game.WildCards.IWildCard;
import java.io.Serializable;

public class Board implements Serializable{
    
    public static int DIMENSION = 20;
    public static int SQUARE = 35;
    
    public IPathCard[] board = new IPathCard[40];
    public IWildCard[] chanceDeck = new IWildCard[16];
    public IWildCard[] communityDeck = new IWildCard[16];
    
}
