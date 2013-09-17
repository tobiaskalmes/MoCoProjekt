package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.game.GameInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
        return null;
    }
}
