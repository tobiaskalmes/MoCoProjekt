package de.htw.toto.moco.server.token;

import de.htw.toto.moco.server.tools.ChecksumHandler;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 14.09.13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public class TokenHandler {
    private TokenHandler instance;

    private TokenHandler() {

    }

    public TokenHandler getInstance() {
        if (instance == null) {
            instance = new TokenHandler();
        }
        return instance;
    }

    public String createToken(String username, int userID) {
        ChecksumHandler handler = ChecksumHandler.getInstance(ChecksumHandler.Type.SHA1);
        handler.update(username);
        handler.update(Integer.toString(userID));
        handler.update(new Date().toString());
        String token = handler.digest();
        //TODO: add token to list
        return token;
    }
}
