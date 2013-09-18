package de.htw.toto.moco.server.messaging;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement()
public class ChatMessageList {
    private List<ChatMessage> messages;

    public ChatMessageList() {
        messages = new ArrayList<ChatMessage>();
    }


    @XmlElements({@XmlElement(type = ChatMessage.class)})
    @XmlElementWrapper
    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}
