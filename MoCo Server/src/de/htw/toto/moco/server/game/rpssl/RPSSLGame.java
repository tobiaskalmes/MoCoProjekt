package de.htw.toto.moco.server.game.rpssl;

import de.htw.toto.moco.server.game.GameBase;
import de.htw.toto.moco.server.game.GameType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class RPSSLGame extends GameBase {
    protected static final GameType gameType = GameType.ROCK_PAPER_SCISSORS_SPOCK_LIZARD;
    private List<GameRule> gameRules;

    public RPSSLGame() {
        super();
        gameRules = new ArrayList<GameRule>();
        initGameRules();
    }

    private void initGameRules() {
        //Rock -> rest
        gameRules.add(new GameRule(GameHand.ROCK, GameHand.PAPER, -1));
        gameRules.add(new GameRule(GameHand.ROCK, GameHand.SCISSORS, 1));
        gameRules.add(new GameRule(GameHand.ROCK, GameHand.SPOCK, -1));
        gameRules.add(new GameRule(GameHand.ROCK, GameHand.LIZARD, 1));
        //Paper -> rest
        gameRules.add(new GameRule(GameHand.PAPER, GameHand.ROCK, 1));
        gameRules.add(new GameRule(GameHand.PAPER, GameHand.SCISSORS, -1));
        gameRules.add(new GameRule(GameHand.PAPER, GameHand.SPOCK, 1));
        gameRules.add(new GameRule(GameHand.PAPER, GameHand.LIZARD, -1));
        //Scissors -> rest
        gameRules.add(new GameRule(GameHand.SCISSORS, GameHand.ROCK, -1));
        gameRules.add(new GameRule(GameHand.SCISSORS, GameHand.PAPER, 1));
        gameRules.add(new GameRule(GameHand.SCISSORS, GameHand.SPOCK, 1));
        gameRules.add(new GameRule(GameHand.SCISSORS, GameHand.LIZARD, -1));
        //Spock -> rest
        gameRules.add(new GameRule(GameHand.SPOCK, GameHand.ROCK, 1));
        gameRules.add(new GameRule(GameHand.SPOCK, GameHand.PAPER, -1));
        gameRules.add(new GameRule(GameHand.SPOCK, GameHand.SCISSORS, 1));
        gameRules.add(new GameRule(GameHand.SPOCK, GameHand.LIZARD, -1));
        //Lizard -> rest
        gameRules.add(new GameRule(GameHand.LIZARD, GameHand.ROCK, -1));
        gameRules.add(new GameRule(GameHand.LIZARD, GameHand.PAPER, 1));
        gameRules.add(new GameRule(GameHand.LIZARD, GameHand.SCISSORS, -1));
        gameRules.add(new GameRule(GameHand.LIZARD, GameHand.SPOCK, 1));
    }

    public void callStop() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {

        }
    }
}
