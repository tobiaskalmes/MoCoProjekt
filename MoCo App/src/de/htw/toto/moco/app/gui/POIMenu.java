package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import de.htw.toto.moco.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 19.09.13
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class POIMenu extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poimenu);


        final Button getPOIButton = (Button) findViewById(R.id.buttonPOIMenuGetPOI);
        getPOIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(POIMenu.this, POISelectActivity.class);
                startActivity(intent);
            }
        });
        final Button navToPOIButton = (Button) findViewById(R.id.buttonPOIMenuNavPOI);
        getPOIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO start nav to poi
            }
        });


    }
}