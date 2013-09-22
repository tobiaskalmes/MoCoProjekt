package de.htw.toto.moco.server.game.rpssl;

import de.htw.toto.moco.server.game.GameBase;
import de.htw.toto.moco.server.game.GameState;
import de.htw.toto.moco.server.game.GameType;
import de.htw.toto.moco.server.game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class RPSSLGame extends GameBase {
    protected static final GameType gameType = GameType.ROCK_PAPER_SCISSORS_SPOCK_LIZARD;
    private final          Object   waitLock = new Object();
    private List<GameRule> gameRules;
    private int            isWaitingForPlayers;
    private GameHand       hand1;
    private GameHand       hand2;
    private int            result;
    private Boolean        keepWaiting;

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
        String playerString = "";
        for (Player player : players) {
            playerString += player.getName() + " ";
        }
        logger.log("Game Rock, Paper, Scissors, Spock, Lizard started with Players: " + playerString, Level.INFO);
        while (isRunning) {
            logger.log("Waiting for Players...", Level.INFO);
            isWaitingForPlayers = 0;
            while (isWaitingForPlayers < 2) {
                //Waiting until every player has played his/her hand
            }
            //get Result
            result = checkResult();
            setGameState(GameState.RESULT_READY);
            logger.log("Player1 played hand: " + hand1 + " Player2 played hand: " + hand2 + " Result: " + result,
                       Level.INFO);
            try {

                Thread.sleep(2000);
            }
            catch (Exception e) {
            }
            keepWaiting = true;
            while (keepWaiting) {
                synchronized (waitLock) {
                    keepWaiting = isWaitingForPlayers > 0;
                }
            }
            setGameState(GameState.WAITING_FOR_PLAYER_ACTION);
        }
    }

    private int checkResult() {
        for (GameRule rule : gameRules) {
            if (rule.getFirstPlayerHand().name().equals(hand1.name()) && rule.getSecondPlayerHand().name().equals
                    (hand2.name())) {
                return rule.getResult();
            }
        }
        return Integer.MIN_VALUE;
    }

    public void playHand(String player, GameHand gameHand) {
        if (player.equals(players.get(0).getName())) {
            if (hand1 == null) {
                ++isWaitingForPlayers;
                hand1 = gameHand;
                logger.log("Player1 played hand: " + gameHand.name(), Level.INFO);
            }
        } else {
            if (hand2 == null) {
                ++isWaitingForPlayers;
                hand2 = gameHand;
                logger.log("Player2 played hand: " + gameHand.name(), Level.INFO);
            }
        }
    }

    public int getResult(String username) {
        if (players.get(0).getName().equals(username)) {
            synchronized (waitLock) {
                --isWaitingForPlayers;
            }
            return result;
        } else {
            synchronized (waitLock) {
                --isWaitingForPlayers;
            }
            return -result;
        }
    }
}
