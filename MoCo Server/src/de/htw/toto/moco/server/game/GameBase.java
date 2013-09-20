package de.htw.toto.moco.server.game;

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
    protected int          gameID;
    protected List<Player> players;
    protected Boolean      isRunning;

    public GameBase() {
        super();
        players = new ArrayList<Player>();
        isRunning = false;
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
}
