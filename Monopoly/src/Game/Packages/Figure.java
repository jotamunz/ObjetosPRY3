
package Game.Packages;

import Game.Token;

public class Figure implements IPackage{
    
    private Token token;
    private int playerId;
    private boolean finalAnswer;
    
    public Figure (int playerId, Token token, boolean finalAnswer){
        this.playerId = playerId;
        this.token = token;
        this.finalAnswer = finalAnswer;
    }

    public Token getToken() {
        return token;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isFinalAnswer() {
        return finalAnswer;
    }

    @Override
    public PackageEnum getType() {
        return PackageEnum.Figure;
    }
    
}
