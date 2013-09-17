package de.htw.toto.moco.app.communication.login;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public interface ILoginListener {

    public void result(String result);

    public void error(Throwable e);
}
