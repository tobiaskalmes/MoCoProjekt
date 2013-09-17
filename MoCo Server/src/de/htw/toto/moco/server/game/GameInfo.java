package de.htw.toto.moco.server.game;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class GameInfo {
    private String   name;
    private int      id;
    private GameType type;

    public GameInfo(String name, int id, GameType type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }
}
