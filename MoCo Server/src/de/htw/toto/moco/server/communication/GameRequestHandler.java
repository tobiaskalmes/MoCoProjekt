package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.database.DBBackend;
import de.htw.toto.moco.server.game.GameFactory;
import de.htw.toto.moco.server.game.GameInfo;
import de.htw.toto.moco.server.game.GameState;
import de.htw.toto.moco.server.game.GameType;
import de.htw.toto.moco.server.game.rpssl.GameHand;

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
        if (!checkToken(token)) {
            return null;
        }
        logger.log("GameList fetched with token " + token, Level.INFO);
        return DBBackend.getInstance().getAllGames();
    }

    @GET
    @Path("/rpssl/{token}/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public String readyForRPSSL(@PathParam("token") String token, @PathParam("username") String username) {
        if (!checkToken(token)) {
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
        if (!checkToken(token)) {
            return null;
        }
        logger.log("User " + username + " has checked the inviteState.", Level.INFO);
        return Integer.toString(GameFactory.getInstance().checkInviteState(username));
    }

    @GET
    @Path("/rpssl/pollGameState/{token}/{username}/{gameID}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pollGameState(@PathParam("token") String token, @PathParam("username") String username,
                                @PathParam("gameID") int gameID) {
        if (!checkToken(token)) {
            return null;
        }
        GameState gameState = GameFactory.getInstance().checkGameState(gameID);
        logger.log(
                "User " + username + " has checked the gamestate for gameID " + gameID + " State: " + gameState.name(),
                Level.INFO);
        return gameState.name();
    }

    @GET
    @Path("/rpssl/playHand/{token}/{username}/{gameID}/{gameHand}")
    @Produces(MediaType.TEXT_PLAIN)
    public String playHand(@PathParam("token") String token, @PathParam("username") String username,
                           @PathParam("gameID") String gameID, @PathParam("gameHand") String gameHand) {
        if (!checkToken(token)) {
            return null;
        }
        logger.log("User " + username + " played Hand: " + gameHand, Level.INFO);
        GameFactory.getInstance().playHand(Integer.parseInt(gameID), username, GameHand.valueOf(gameHand));
        return "true";
    }

    @GET
    @Path("/rpssl/getResult/{token}/{username}/{gameID}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getResult(@PathParam("token") String token, @PathParam("username") String username,
                            @PathParam("gameID") String gameID) {
        if (!checkToken(token)) {
            return null;
        }
        logger.log("User " + username + " fetched Result for gameID " + gameID, Level.INFO);
        return GameFactory.getInstance().checkResult(Integer.parseInt(gameID), username).toString();
    }
}
