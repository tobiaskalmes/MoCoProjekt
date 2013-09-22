package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.poi.IPOIListener;
import de.htw.toto.moco.app.communication.poi.POIRequester;
import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 21.09.13
 * Time: 12:37
 * To change this template use File | Settings | File Templates.
 */
public class POISelectActivity extends Activity implements IPOIListener {
    ListView        poiListView;
    POIArrayAdapter poiArrayAdapter;
    POI[]           data;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.poilist);
        poiListView = (ListView) findViewById(R.id.poiListView);
        POIRequester.requestPOIList(getBaseContext(), POISelectActivity.this);


    }

    @Override
    public void result(POIList result) {
        data = new POI[result.getPois().size()];
        data = result.getPois().toArray(data);
        poiArrayAdapter = new POIArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_activated_1, data);
        poiListView.setAdapter(poiArrayAdapter);
        poiListView.invalidate();
    }

    @Override
    public void error(Throwable e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}