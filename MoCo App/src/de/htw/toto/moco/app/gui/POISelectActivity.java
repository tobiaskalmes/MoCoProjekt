package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.poi.IPOIListener;
import de.htw.toto.moco.app.communication.poi.POIRequester;
import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 21.09.13
 * Time: 12:37
 * To change this template use File | Settings | File Templates.
 */
public class POISelectActivity extends ListActivity implements IPOIListener {
    ListView        poiListView;
    ArrayAdapter<POI> poiArrayAdapter;
    POI[]           data;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.poilist);
        poiListView = (ListView) findViewById(android.R.id.list);
        POIRequester.requestPOIList(getBaseContext(), POISelectActivity.this);


    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a friend item from list is clicked
        Intent intent = new Intent(POISelectActivity.this, POIDetail.class);

        intent.putExtra("POI", data[position]);
        startActivity(intent);
    }

    @Override
    public void result(POIList result) {
        data = new POI[result.getPois().size()];
        data = result.getPois().toArray(data);
        poiArrayAdapter = new POIArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_activated_1, data);
        poiListView.setAdapter(poiArrayAdapter);

    }

    @Override
    public void error(Throwable e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}