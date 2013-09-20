package de.htw.toto.moco.app.communication.message;

import de.htw.toto.moco.server.messaging.ChatMessageList;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */
public interface IMessageListener {
    public void resultChatMessageList(ChatMessageList chatMessages);

    public void resultNewChatMessage(Boolean result);

    public void error(Throwable e);
}
