package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;
import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Path("/message")
public class Message {
    private static RootLogger logger = RootLogger.getInstance(LoggerNames.MAIN_LOGGER);

    @GET
    @Path(value = "/list")
    @Produces(MediaType.TEXT_XML)
    public List<ChatMessage> getChatMessages() {
        //TODO: add db request
        //marshall the messages
        ChatMessageList cml = new ChatMessageList();
        List<ChatMessage> cm = new ArrayList<ChatMessage>();
        cm.add(new ChatMessage("senderString", "receiverString", "contentString", 1234));
        cml.setMessages(cm);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ChatMessageList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(cml, System.out);
        }
        catch (Exception e) {
            logger.log("JAXB-Exception", Level.SEVERE, e);
        }

        return null;
    }
}
