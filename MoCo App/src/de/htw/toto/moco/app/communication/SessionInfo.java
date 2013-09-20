package de.htw.toto.moco.app.communication;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public class SessionInfo {
    private static SessionInfo instance;
    private        String      username;
    private        String      token;

    private SessionInfo() {
    }

    public static SessionInfo getInstance() {
        if (instance == null) {
            instance = new SessionInfo();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
