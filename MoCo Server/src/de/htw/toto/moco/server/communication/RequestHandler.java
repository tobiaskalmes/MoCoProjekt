package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class RequestHandler {
    protected static RootLogger logger = RootLogger.getInstance(LoggerNames.MAIN_LOGGER);
}
