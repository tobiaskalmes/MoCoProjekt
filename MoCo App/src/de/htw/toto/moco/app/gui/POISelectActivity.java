package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.os.Bundle;
import de.htw.toto.moco.app.communication.poi.IPOIListener;
import de.htw.toto.moco.server.navigation.POI;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 21.09.13
 * Time: 12:37
 * To change this template use File | Settings | File Templates.
 */
public class POISelectActivity extends Activity implements IPOIListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void result(List<POI> result) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void error(Throwable e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}