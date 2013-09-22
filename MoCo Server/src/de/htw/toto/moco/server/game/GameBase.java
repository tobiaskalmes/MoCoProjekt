package de.htw.toto.moco.server.game;

import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 17:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class GameBase extends Thread {
    protected static Integer runningGameID = 1000000;
    protected final  Object  gameStateLock = new Object();
    protected int          gameID;
    protected List<Player> players;
    protected Boolean      isRunning;
    protected RootLogger   logger;
    private   GameState    gameState;

    public GameBase() {
        super();
        synchronized (runningGameID) {
            gameID = runningGameID++;
        }
        logger = RootLogger.getInstance(LoggerNames.MAIN_LOGGER);
        players = new ArrayList<Player>();
        isRunning = false;
        gameState = GameState.WAITING_FOR_PLAYER_ACTION;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public synchronized GameState getGameState() {
        return gameState;
    }

    protected synchronized void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
