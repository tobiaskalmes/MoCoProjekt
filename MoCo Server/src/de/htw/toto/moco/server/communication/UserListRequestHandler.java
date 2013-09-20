package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.database.DBBackend;
import de.htw.toto.moco.server.user.UserList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
@Path("/user")
public class UserListRequestHandler extends RequestHandler {
    @GET
    @Path("/list/{token}/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getChatMessages(@PathParam("token") String token,
                                    @PathParam("username") String username) {
        if (!checkToken(token)) {
            return null;
        }
        logger.log("Fetched friendlist for user: " + username + " with token: " + token, Level.INFO);
        return DBBackend.getInstance().getFriendlist(username);
    }

    @GET
    @Path("/addFriend/{token}/{username}/{friend}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addMessage(@PathParam("token") String token, @PathParam("username") String username,
                             @PathParam("friend") String friend) {
        if (!checkToken(token)) {
            return null;
        }
        DBBackend.getInstance().addFriend(username, friend);
        logger.log("User " + username + " added " + friend + " as friend.", Level.INFO);
        return "true";
    }
}
