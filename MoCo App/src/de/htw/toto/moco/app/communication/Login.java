package de.htw.toto.moco.app.communication;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 14.09.13
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class Login {
    private Login instance;

    private Login() {
    }

    public Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }


}
