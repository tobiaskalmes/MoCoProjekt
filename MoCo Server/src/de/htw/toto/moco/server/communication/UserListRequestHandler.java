package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.token.TokenHandler;
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
public class UserListRequestHandler extends RequestHandler {
    @GET
    @Path(value = "/list/{username}/{token}")
    @Produces(MediaType.TEXT_XML)
    public UserList getChatMessages(@PathParam("token") String token,
                                    @PathParam("username") String username) {
        if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return null;
        }
        return null;// DBBackend.getInstance().getFriendlist(username);
    }
}
