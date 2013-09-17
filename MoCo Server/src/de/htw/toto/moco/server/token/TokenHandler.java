package de.htw.toto.moco.server.token;

import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;
import de.htw.toto.moco.server.tools.ChecksumHandler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 14.09.13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public class TokenHandler {
    private static final long TIME_OUT_CHECK_INTERVAL = 60 * 1000L;
    private static final long TIME_OUT_DURATION       = 30 * 60 * 1000L;
    private static TokenHandler instance;
    private RootLogger logger = RootLogger.getInstance(LoggerNames.MAIN_LOGGER);
    private Thread                           timeOutHandler;
    private ConcurrentHashMap<String, Token> tokenMap;
    private boolean                          keepRunning;

    private TokenHandler() {
        tokenMap = new ConcurrentHashMap<String, Token>();
        keepRunning = true;
        timeOutHandler = new Thread() {
            @Override
            public void run() {
                logger.log("TokenHandler - TimeOutHandler started...", Level.INFO);
                while (keepRunning) {
                    try {
                        for (Map.Entry<String, Token> t : tokenMap.entrySet()) {
                            if (t.getValue().getLastUpdated().getTime() + TIME_OUT_DURATION < new Date().getTime()) {
                                tokenMap.remove(t.getKey());
                                logger.log("Removed Token for user " + t.getKey() + ".", Level.INFO);
                            }
                        }
                        Thread.sleep(TIME_OUT_CHECK_INTERVAL);
                    }
                    catch (Exception e) {
                        logger.log("ThreadingException", Level.SEVERE, e);
                    }
                }
            }
        };
        timeOutHandler.start();
    }

    public static TokenHandler getInstance() {
        if (instance == null) {
            instance = new TokenHandler();
        }
        return instance;
    }

    public boolean checkToken(String token) {
        for (Map.Entry<String, Token> t : tokenMap.entrySet()) {
            if (t.getValue().getTokenString().equals(token)) {
                return true;
            }
        }
        return false;
    }

    public String createToken(String username) {
        ChecksumHandler handler = ChecksumHandler.getInstance(ChecksumHandler.Type.SHA1);
        handler.update(username);
        Date tokenDate = new Date();
        handler.update(tokenDate.toString());
        String token = handler.digest();
        tokenMap.put(username, new Token(token, tokenDate));
        logger.log("Added token for user " + username + ".", Level.INFO);
        return token;
    }

    public void removeToken(String username) {
        for (Map.Entry<String, Token> t : tokenMap.entrySet()) {
            if (t.getKey().equals(username)) {
                tokenMap.remove(t.getKey());
                logger.log("Removed Token for user " + t.getKey() + ".", Level.INFO);
            }
        }
    }
}
