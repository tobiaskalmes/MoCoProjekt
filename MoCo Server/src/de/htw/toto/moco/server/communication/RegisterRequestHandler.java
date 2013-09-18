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
 * Date: 18.09.13
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
@Path("/register")
public class RegisterRequestHandler extends RequestHandler {
    @GET
    @Path("/{username}/{passwordHash}")
    @Produces(MediaType.TEXT_PLAIN)
    public String register(@PathParam("username") String username, @PathParam("passwordHash") String passwordHash) {
        //check if username is free
        if (DBBackend.getInstance().getIdUserByName(username) >= 1) {
            return null;
        }
        //register new user
        DBBackend.getInstance().addUser(username, passwordHash);
        logger.log("Added user " + username, Level.INFO);
        return TokenHandler.getInstance().createToken(username);
    }

}
