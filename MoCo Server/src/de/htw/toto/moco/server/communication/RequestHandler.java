package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;
import de.htw.toto.moco.server.token.TokenHandler;

import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class RequestHandler {
    protected static RootLogger logger = RootLogger.getInstance(LoggerNames.MAIN_LOGGER);

    protected boolean checkToken(String token) {
        /*if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return false;
        }*/
        return true;
    }
}
