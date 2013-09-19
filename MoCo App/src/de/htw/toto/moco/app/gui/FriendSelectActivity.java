package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.message.IMessageListener;
import de.htw.toto.moco.app.communication.message.MessageRequester;
import de.htw.toto.moco.server.messaging.ChatMessageList;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class FriendSelectActivity extends ListActivity { //TODO User Request...  //TODO ADDFRIENDIMPLEMENT!
    String[] testItems= { "this", "is", "a", "really", "silly", "list" };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.chatpartnerlist);

        ListView friendListView;

        ArrayAdapter arrayAdapter;

        friendListView = (ListView)findViewById(R.id.list);
        arrayAdapter =new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,testItems);
        friendListView.setAdapter(arrayAdapter);





        final Button addFriendButton  = (Button) findViewById(R.id.addFriendButton);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO start Add friend to fl
                //TODO update List!
            }
        });
        //anzeigen der liste
        //auswahl durch onSelect oder so
        //dann nächste anctivity, übergebe FREUND!
        //Notification durch Message?

        //Intent intent = new Intent(FriendSelectActivity.this, ChatActivity.class);
        //startActivity(intent);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       // Do something when a friend item from list is clicked
        Intent intent = new Intent(FriendSelectActivity.this, ChatActivity.class);
        intent.putExtra("CHATBUDDY", testItems[position]);
        startActivity(intent);
    }

}