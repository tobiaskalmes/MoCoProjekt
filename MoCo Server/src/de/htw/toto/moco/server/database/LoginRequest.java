package de.htw.toto.moco.server.database;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 14.09.13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class LoginRequest extends DBRequest {
    private String password;
    private String username;

    public LoginRequest(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
