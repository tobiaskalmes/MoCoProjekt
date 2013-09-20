package de.htw.toto.moco.server.game;

import de.htw.toto.moco.server.game.rpssl.GameHand;
import de.htw.toto.moco.server.game.rpssl.RPSSLGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public class GameFactory {
    private static GameFactory                 instance;
    private        List<GameBase>              games;
    private        Map<GameType, List<String>> waitList;
    private        Map<String, GameBase>       readyGames;

    private GameFactory() {
        games = new ArrayList<GameBase>();
        waitList = new HashMap<GameType, List<String>>();
        waitList.put(GameType.ROCK_PAPER_SCISSORS_SPOCK_LIZARD, new ArrayList<String>());
        readyGames = new HashMap<String, GameBase>();
    }

    public static GameFactory getInstance() {
        if (instance == null) {
            instance = new GameFactory();
        }
        return instance;
    }

    public void addToWaitList(GameType gameType, String username) {
        waitList.get(gameType).add(username);
        checkWaiList(gameType);
    }

    private void checkWaiList(GameType gameType) {
        List<String> waitingPlayers = waitList.get(gameType);
        if (waitingPlayers.size() >= 2) {
            //enough player ready
            String player1 = waitingPlayers.remove(0);
            String player2 = waitingPlayers.remove(0);
            GameBase game = createRPSSL();
            game.addPlayer(new Player(player1));
            game.addPlayer(new Player(player2));
            game.start();
            readyGames.put(player1, game);
            readyGames.put(player2, game);
        }
    }

    public GameBase createRPSSL() {
        RPSSLGame game = new RPSSLGame();
        games.add(game);
        return game;
    }

    public GameState checkGameState(int gameID) {
        for (GameBase game : games) {
            if (game.getGameID() == gameID) {
                return game.getGameState();
            }
        }
        return null;
    }

    public int checkInviteState(String username) {
        if (readyGames.containsKey(username)) {
            return readyGames.get(username).getGameID();
        }
        return -1;
    }

    public void playHand(int gameID, String username, GameHand gameHand) {
        for (GameBase game : games) {
            if (game.getGameID() == gameID) {
                ((RPSSLGame) game).playHand(username, gameHand);
            }
        }
    }

    public Integer checkResult(int gameID, String username) {
        for (GameBase game : games) {
            if (game.getGameID() == gameID) {
                return ((RPSSLGame) game).getResult(username);
            }
        }
        return Integer.MIN_VALUE;
    }
}
