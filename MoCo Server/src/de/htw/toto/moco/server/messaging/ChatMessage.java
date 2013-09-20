package de.htw.toto.moco.server.messaging;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlType(propOrder = {"sender", "receiver", "content", "id", "sendTime"})
public class ChatMessage {
    private String sender;
    private String receiver;
    private String content;
    private int    id;
    private long   sendTime;

    public ChatMessage() {
    }

    public ChatMessage(String sender, String receiver, String content, int id, long sendTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.id = id;
        this.sendTime = sendTime;
    }

    public ChatMessage(String sender, String receiver, String content, int id) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.id = id;
        sendTime = new Date().getTime();
    }



    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
