package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.server.messaging.ChatMessageList;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class ChatActivity extends ListActivity {
    ChatMessageList chatMessages;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chat);


        ListView chatListView;

        ArrayAdapter arrayAdapter;

        chatListView = (ListView)findViewById(android.R.id.list);





        /*setListAdapter(new ArrayAdapter<String>(
                ChatActivity.this,
                android.R.layout.simple_list_item_activated_1,));
          */




        //zeige alle messages an zwischen mir und freund
        //Sende Nachricht,
        //erneueter abruf
        //eventuell pollen um neue nachrichten zu erhalten?
    }
}