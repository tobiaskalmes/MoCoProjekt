package de.htw.toto.moco.server.game;

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

    private GameFactory() {
        games = new ArrayList<GameBase>();
        waitList = new HashMap<GameType, List<String>>();
        waitList.put(GameType.ROCK_PAPER_SCISSORS_SPOCK_LIZARD, new ArrayList<String>());
    }

    public static GameFactory getInstance() {
        if (instance == null) {
            instance = new GameFactory();
        }
        return instance;
    }

    public void addToWaitList(GameType gameType, String username) {
        waitList.get(gameType).add(username);
        //TODO: check if enough players are in the wait list
    }

    public GameBase createRPSSL() {
        RPSSLGame game = new RPSSLGame();
        games.add(game);
        return game;
    }
}
