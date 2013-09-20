package de.htw.toto.moco.app.tools.gps;

import android.location.Location;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 13:48
 * To change this template use File | Settings | File Templates.
 */
public interface IGPSLocationListener {
    public void onLocationChanged(Location location);
}
