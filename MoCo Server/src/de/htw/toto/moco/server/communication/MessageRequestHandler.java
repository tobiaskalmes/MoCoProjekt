package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.database.DBBackend;
import de.htw.toto.moco.server.messaging.ChatMessageList;
import de.htw.toto.moco.server.token.TokenHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Path("/message")
public class MessageRequestHandler extends RequestHandler {

    @GET
    @Path(value = "/list/{username}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatMessageList getChatMessages(@PathParam("token") String token, @PathParam("username") String username) {
        if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return null;
        }
        logger.log("username: " + username + " token: " + token, Level.INFO);
        return DBBackend.getInstance().getAllChatMessages(username);
    }

    @POST
    @Path(value = "/{token}/addMessage")
    @Consumes("application/xml")
    public void addMessage(@PathParam("token") String token, String xml) {
        if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return;
        }
        logger.log(xml, Level.FINEST);
    }
}
