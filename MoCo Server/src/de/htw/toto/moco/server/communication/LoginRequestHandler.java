package de.htw.toto.moco.server.communication;

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
    public String login(@PathParam(value = "username") String username, @PathParam(value = "passwordHash") String
            passwordHash) {
        //TODO: add password check
        //TODO: return token
        return "<html><body>username: " + username + "<br/>passwordHash:" + passwordHash + "</body></html>";
    }
}