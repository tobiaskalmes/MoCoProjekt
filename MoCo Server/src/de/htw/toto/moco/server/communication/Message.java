package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.messaging.ChatMessage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Path("/message")
public class Message {
    @GET
    @Path(value = "/list")
    @Produces(MediaType.TEXT_XML)
    public List<ChatMessage> getChatMessages() {
        //TODO: add db request
        return null;
    }
}
