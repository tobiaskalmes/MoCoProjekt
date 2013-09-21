package de.htw.toto.moco.app.gui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.user.IUserListener;
import de.htw.toto.moco.app.communication.user.UserRequester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class FriendSelectActivity extends ListActivity implements
                                                       IUserListener { //TODO User Request...  //TODO ADDFRIENDIMPLEMENT!

    ArrayList<String> userFriends;
    ListView          friendListView;
    ArrayAdapter      arrayAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.chatpartnerlist);


        UserRequester.requestUserList(getBaseContext(), FriendSelectActivity.this);

        friendListView = (ListView) findViewById(android.R.id.list);


        final Button addFriendButton = (Button) findViewById(R.id.addFriendButton);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserRequester.requestAddFriend(getBaseContext(), FriendSelectActivity.this,
                                               ((EditText) findViewById(R.id.addFriendName)).getText().toString());

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

        intent.putExtra("CHATBUDDY", (String) userFriends.toArray()[position]);
        startActivity(intent);
    }

    @Override
    public void resultUserList(List<String> users) {

        userFriends = new ArrayList<String>(users);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, userFriends.toArray());
        friendListView.setAdapter(arrayAdapter);
    }

    @Override
    public void resultAddFriend(Boolean result) {
        ((TextView) findViewById(R.id.addFriendResult)).setText(
                "Successfully Added:" + ((EditText) findViewById(R.id.addFriendName)).getText().toString());
        //refresh
        UserRequester.requestUserList(getBaseContext(), FriendSelectActivity.this);
        ((EditText) findViewById(R.id.addFriendName)).setText("");
    }

    @Override
    public void error(Throwable e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}