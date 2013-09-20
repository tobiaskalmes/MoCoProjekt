package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.game.GameFactory;
import de.htw.toto.moco.server.game.GameInfo;
import de.htw.toto.moco.server.game.GameType;

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
    @Path("/list/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GameInfo> getGameInfoList(@PathParam("token") String token) {
        //TODO: add db request
        if (checkToken(token)) {
            return null;
        }
        return null;
    }

    @GET
    @Path("/rpssl/{token}/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public String readyForRPSSL(@PathParam("token") String token, @PathParam("username") String username) {
        if (checkToken(token)) {
            return null;
        }
        GameFactory.getInstance().addToWaitList(GameType.ROCK_PAPER_SCISSORS_SPOCK_LIZARD, username);
        logger.log("Added " + username + " to WaiList for " + GameType.ROCK_PAPER_SCISSORS_SPOCK_LIZARD, Level.INFO);
        return "true";
    }

    @GET
    @Path("/rpssl/inviteState/{token}/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkInviteState(@PathParam("token") String token, @PathParam("username") String username) {
        if (checkToken(token)) {
            return null;
        }
        //check if game is ready
        return "true";
    }
}
