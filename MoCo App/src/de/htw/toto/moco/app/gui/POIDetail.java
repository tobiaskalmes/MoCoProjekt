package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.tools.gps.GPSLocation;
import de.htw.toto.moco.server.navigation.POI;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 22.09.13
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class POIDetail extends Activity {
    POI poi;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poidetail);
        poi = (POI)getIntent().getSerializableExtra("POI");

        ((TextView) findViewById(R.id.poiDetailName)).setText("Name:" + poi.getName());
        ((TextView) findViewById(R.id.poiDetailLat)).setText("Lat:" + String.valueOf(poi.getLatitude()));
        ((TextView) findViewById(R.id.poiDetailLong)).setText("Long:" + String.valueOf(poi.getLongitude()));
        ((TextView) findViewById(R.id.poiDetailName)).setText(GPSLocation.getInstance().);


    }


}