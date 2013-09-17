package de.htw.toto.moco.server.messaging;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessage {
    private String sender;
    private String receiver;
    private String content;
    private int    id;

    public ChatMessage(String sender, String receiver, String content, int id) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }
}
