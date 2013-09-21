package de.htw.toto.moco.app.communication.game;

import de.htw.toto.moco.server.game.GameInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 21.09.13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public interface IGameInfoListener {
    public void result(List<GameInfo> result);

    public void error(Throwable e);
}
