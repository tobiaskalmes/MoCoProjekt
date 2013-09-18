package de.htw.toto.moco.app.communication.register;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 18.09.13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public interface IRegisterListener {
    public void registerResult(String token);

    public void error(Throwable e);
}
