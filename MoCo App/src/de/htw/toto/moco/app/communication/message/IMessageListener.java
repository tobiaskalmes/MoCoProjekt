package de.htw.toto.moco.app.communication.message;

import de.htw.toto.moco.server.messaging.ChatMessage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */
public interface IMessageListener {
    public void result(List<ChatMessage> chatMessages);

    public void error(Throwable e);
}
