package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.token.TokenHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    @Path(value = "/{username}/{passwordHash}")
    @Produces(MediaType.TEXT_HTML)
    public String login(@PathParam("username") String username, @PathParam("passwordHash") String
            passwordHash) {
        //TODO: add password check
        return TokenHandler.getInstance().createToken(username);
    }
}
