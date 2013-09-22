package de.htw.toto.moco.app.communication.game.RPSSL;

import de.htw.toto.moco.server.game.GameState;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 22.09.13
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
public interface IRPSSLRequester {
    public void readyForRPSSLResult(String result);

    public void checkInviteStateResult(Integer inviteState);

    public void pollGameStateResult(GameState gameState);

    public void playHandResult(String result);

    public void gameResult(Integer result);

    public void error(Throwable e);
}
