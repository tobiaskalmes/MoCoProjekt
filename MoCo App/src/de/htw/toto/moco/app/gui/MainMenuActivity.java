package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.login.LoginRequester;
import de.htw.toto.moco.server.tools.ChecksumHandler;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mainmenu);

        final Button startChatButton = (Button) findViewById(R.id.buttonMainChat);
        startChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //TODO start chat
            }
        });

        final Button startNavigationButton  = (Button) findViewById(R.id.buttonMainNavigate);
        startNavigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO start navigation
            }
        });

        final Button poiButton = (Button) findViewById(R.id.buttonMainPoi);
        poiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO start poi
            }
        });
        final Button gameButton = (Button) findViewById(R.id.buttonMainGame);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO start game
            }
        });

        //todo logout button?


    }

}