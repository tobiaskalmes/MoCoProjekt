package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import de.htw.toto.moco.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
public class GameMenu extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gamesmenu);


        final Button gameListButton = (Button) findViewById(R.id.buttonGameMenuGameList);
        gameListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}