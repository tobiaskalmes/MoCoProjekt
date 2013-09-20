package de.htw.toto.moco.server.game.rpssl;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
public class GameRule {
    private GameHand firstPlayerHand;
    private GameHand secondPlayerHand;
    /**
     * result > 0 Player 1 wins, result < 0 Player 2 wins, result = 0 pat
     */
    private int      result;

    public GameRule(GameHand firstPlayerHand, GameHand secondPlayerHand, int result) {
        this.firstPlayerHand = firstPlayerHand;
        this.secondPlayerHand = secondPlayerHand;
        this.result = result;
    }

    public GameHand getFirstPlayerHand() {
        return firstPlayerHand;
    }

    public void setFirstPlayerHand(GameHand firstPlayerHand) {
        this.firstPlayerHand = firstPlayerHand;
    }

    public GameHand getSecondPlayerHand() {
        return secondPlayerHand;
    }

    public void setSecondPlayerHand(GameHand secondPlayerHand) {
        this.secondPlayerHand = secondPlayerHand;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
