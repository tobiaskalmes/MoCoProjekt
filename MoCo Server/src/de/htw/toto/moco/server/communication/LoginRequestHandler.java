package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.database.DBBackend;
import de.htw.toto.moco.server.token.TokenHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 14.09.13
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */

@Path("/login")
public class LoginRequestHandler extends RequestHandler {
    @GET
    @Path("/{username}/{passwordHash}")
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@PathParam("username") String username, @PathParam("passwordHash") String
            passwordHash) {
        if (!DBBackend.getInstance().verifyUserPassword(username, passwordHash)) {
            return null;
        }
        logger.log("User " + username + " logged in.", Level.INFO);
        return TokenHandler.getInstance().createToken(username);
    }
}
