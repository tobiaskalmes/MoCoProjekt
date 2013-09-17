package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.game.GameInfo;
import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;
import de.htw.toto.moco.server.token.TokenHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:05
 * To change this template use File | Settings | File Templates.
 */
@Path("/game")
public class GameRequestHandler extends RequestHandler {
    @GET
    @Path(value = "/list/{token}")
    @Produces(MediaType.TEXT_XML)
    public List<GameInfo> getGameInfoList(@PathParam(value = "token") String token) {
        //TODO: add db request
        if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return null;
        }
        return null;
    }
}
