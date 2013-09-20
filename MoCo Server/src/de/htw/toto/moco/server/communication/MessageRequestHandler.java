package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.database.DBBackend;
import de.htw.toto.moco.server.messaging.ChatMessageList;
import de.htw.toto.moco.server.token.TokenHandler;
import de.htw.toto.moco.server.tools.JSONParser;

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
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Path("/message")
public class MessageRequestHandler extends RequestHandler {

    @GET
    @Path("/list/{token}/{username}/{chatPartner}")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatMessageList getChatMessages(@PathParam("token") String token, @PathParam("username") String username,
                                           @PathParam("chatPartner") String chatPartner) {
        if (!checkToken(token)) {
            return null;
        }
        logger.log("username: " + username + " chatPartner: " + chatPartner + " token: " + token, Level.INFO);
        return DBBackend.getInstance().getAllChatMessages(username, chatPartner);
    }

    @GET
    @Path("/addMessage/{token}/{message}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addMessage(@PathParam("token") String token, @PathParam("message") String messageJSONString) {
        if (!checkToken(token)) {
            return null;
        }
        DBBackend.getInstance().addChatMessage(JSONParser.parseToChatMessage(messageJSONString));
        logger.log("Received message: " + messageJSONString + " with token: " + token, Level.INFO);
        return "true";
    }
}
