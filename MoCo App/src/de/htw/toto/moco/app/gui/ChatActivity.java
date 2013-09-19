package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import de.htw.toto.moco.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class ChatActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chatpartnerlist);


        //zeige alle messages an zwischen mir und freund
        //
    }
}