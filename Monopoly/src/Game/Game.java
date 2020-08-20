
package Game;

import Game.PathCards.CardDraw;
import Game.PathCards.CardDraw.DrawType;
import Game.PathCards.*;
import Game.WildCards.IWildCard;
import javafx.util.Pair;

public class Game {
    
    private static int chanceIndex = 0;
    private static int communityIndex = 0;
    
    public static Pair<Integer, Boolean> movePlayer(int currentPosition, int amount, int pathSize, boolean collectGo){
        if (currentPosition + amount >= pathSize){
            if(collectGo)
                return new Pair(currentPosition+amount-pathSize, true);
            else
                return new Pair(currentPosition+amount-pathSize, false);
        }
        return new Pair(currentPosition+amount, false);
    }
    
    public static Pair<Integer, Integer> rollDice(){
        int x = (int) ((Math.random()*100)%6)+1;
        int y = (int) ((Math.random()*100)%6)+1;
        return new Pair(x, y);
    }
    
    public static IPathCard getCardAt(Board board, int position){
        return board.board[position];
    }
    
    public static IWildCard drawWildCard(Board board, CardDraw cardDraw){
        IWildCard card;
        if (cardDraw.type == DrawType.Chance){
            card = board.chanceDeck[chanceIndex];
            if (chanceIndex + 1 >= board.chanceDeck.length)
                chanceIndex = 0;
            else
                chanceIndex++;
        }
        else{
            card = board.communityDeck[communityIndex];
            if (communityIndex + 1 >= board.communityDeck.length)
                communityIndex = 0;
            else
                communityIndex++;
        }
        return card;
    }
    
    public static int findDestination(Board board, String name, int currentPos){
        for (int i = 0; i < board.board.length; i++){
            if (board.board[i].getName().equals(name)){
                if (i < currentPos)
                    return (39 - currentPos) + i;
                else
                    return i - currentPos;
            }
        }
        return 0;
    }
    
    public static int findNearest(Board board, PathCardType type, int currentPos){
        for (int i = 0; i < board.board.length; i++){
            if (board.board[i].getType() == type){
                if (i < currentPos)
                    return (39 - currentPos) + i;
                else
                    return i - currentPos;
            }
        }
        return 0;        
    }
    
    public static Property findPropertyToBuild(Board board, Property oldCard){
        for (int i = 0; i < board.board.length; i++){
            if (board.board[i].getName().equals(oldCard.getName())){
                return (Property) board.board[i];   
            }
        }
        return null;
    }
    
    public static void updatePlayerBuilds(Player player, Property card){
        for (int i = 0; i < player.properties.size(); i++){
            if (player.properties.get(i).getType() == PathCardType.Property){
                if (((Property)player.properties.get(i)).name.equals(card.getName())){
                    ((Property) player.properties.get(i)).hotel = card.hotel;
                    ((Property) player.properties.get(i)).houses = card.houses;
                }
            }
        }
    }
    
    public static void updateEnemyBuilds(Player player, Property card, int enemyId){
        if (player.enemyProperties.get(enemyId) != null){
            for (int i = 0; i < player.enemyProperties.get(enemyId).size(); i++){
                if (player.enemyProperties.get(enemyId).get(i).getType() == PathCardType.Property){
                    if (((Property)player.enemyProperties.get(enemyId).get(i)).name.equals(card.getName())){
                        ((Property) player.enemyProperties.get(enemyId).get(i)).hotel = card.hotel;
                        ((Property) player.enemyProperties.get(enemyId).get(i)).houses = card.houses;
                    }
                }
            }
        }
    }
}
