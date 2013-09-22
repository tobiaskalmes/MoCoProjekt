package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.message.IMessageListener;
import de.htw.toto.moco.app.communication.message.MessageRequester;
import de.htw.toto.moco.app.communication.user.UserRequester;
import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class ChatActivity extends Activity implements IMessageListener {
    ChatMessage[]    data;
    String           chatPartner;
    ChatArrayAdapter chatArrayAdapter;
    private ListView chatListView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chat);

        chatPartner = getIntent().getStringExtra("CHATBUDDY");

        ((TextView) findViewById(R.id.chatPartner)).setText(chatPartner);
        ((TextView) findViewById(R.id.chatPartner)).setTextColor(Color.MAGENTA);

        final Button sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageRequester.requestNewChatMessage(getBaseContext(), ChatActivity.this, chatPartner,
                                                       ((EditText) findViewById(
                                                               R.id.sendMessageContent)).getText().toString());

            }
        });
        //zeige alle messages an zwischen mir und freund
        //Sende Nachricht,
        //erneueter abruf
        //eventuell pollen um neue nachrichten zu erhalten?
    }

    @Override
    public void resultChatMessageList(ChatMessageList chatMessages) {
        data = new ChatMessage[chatMessages.getMessages().size()];
        data = chatMessages.getMessages().toArray(data);

        chatArrayAdapter = new ChatArrayAdapter(this, android.R.layout.simple_list_item_activated_1, data);
        chatListView = (ListView) findViewById(R.id.chatListView);
        chatListView.setAdapter(chatArrayAdapter);
    }

    @Override
    public void resultNewChatMessage(Boolean result) {
        ((EditText) findViewById(R.id.sendMessageContent)).setText("");
        //refresh
        MessageRequester.requestChatMessages(getBaseContext(), ChatActivity.this, chatPartner);

    }

    @Override
    public void error(Throwable e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}