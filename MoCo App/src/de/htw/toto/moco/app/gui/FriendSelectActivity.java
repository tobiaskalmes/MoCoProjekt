package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import de.htw.toto.moco.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class FriendSelectActivity extends Activity { //TODO User Request...
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chatpartnerlist);





        //anzeigen der liste
        //auswahl durch onSelect oder so
        //dann nächste anctivity, übergebe FREUND!
        //Notification durch Message?
        Intent intent = new Intent(FriendSelectActivity.this, GameMenu.class);
        startActivity(intent);
    }
}