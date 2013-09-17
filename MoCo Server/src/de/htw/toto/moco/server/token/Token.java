package de.htw.toto.moco.server.token;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
public class Token {
    private String tokenString;
    private Date   lastUpdated;

    public Token(String tokenString, Date creationDate) {
        this.tokenString = tokenString;
        this.lastUpdated = creationDate;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
