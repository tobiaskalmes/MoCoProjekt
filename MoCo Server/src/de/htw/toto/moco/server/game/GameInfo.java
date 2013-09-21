package de.htw.toto.moco.server.game;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlType(propOrder = {"name", "id", "type"})
public class GameInfo {
    private String   name;
    private int      id;
    private GameType type;

    public GameInfo() {
    }

    public GameInfo(String name, int id, GameType type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }
}
