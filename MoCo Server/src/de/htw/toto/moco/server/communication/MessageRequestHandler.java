package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
    @Path(value = "/list/{token}")
    @Produces(MediaType.TEXT_XML)
    public ChatMessageList getChatMessages(@PathParam(value = "token")String token) {
        //TODO: add db request
        //marshall the messages
        ChatMessageList cml = new ChatMessageList();
        List<ChatMessage> cm = new ArrayList<ChatMessage>();
        cm.add(new ChatMessage("senderString", "receiverString", "contentString", 1234));
        cml.setMessages(cm);
        return cml;
    }
}
