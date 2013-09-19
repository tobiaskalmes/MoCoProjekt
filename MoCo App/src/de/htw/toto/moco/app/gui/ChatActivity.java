package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.htw.toto.moco.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class ChatActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chat);



        String[] testItems = { "this", "is", "a", "really",
                "silly", "list" };



        setListAdapter(new ArrayAdapter<String>(
                ChatActivity.this,
                android.R.layout.simple_list_item_activated_1,
                testItems));





        //zeige alle messages an zwischen mir und freund
        //Sende Nachricht,
        //erneueter abruf
        //eventuell pollen um neue nachrichten zu erhalten?
    }
}