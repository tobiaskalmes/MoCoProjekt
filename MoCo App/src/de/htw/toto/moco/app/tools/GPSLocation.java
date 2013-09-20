package de.htw.toto.moco.app.tools;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class GPSLocation implements LocationListener {
    private static GPSLocation                instance;
    private        Context                    context;
    private        Location                   currentLocation;
    private        Location                   destination;
    private        List<IGPSLocationListener> listeners;

    private GPSLocation() {
        listeners = new ArrayList<IGPSLocationListener>();
    }

    public static GPSLocation getInstance() {
        if (instance == null) {
            instance = new GPSLocation();
        }
        return instance;
    }

    public void addListener(IGPSLocationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IGPSLocationListener listener) {
        listeners.remove(listener);
    }

    private void init() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setContext(Context context) {
        this.context = context;
        init();
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        if (destination == null) {
            destination = new Location(location);
            destination.setLongitude(location.getLongitude() + 5);
            destination.setLatitude(location.getLatitude() + 5);
        }
        float bearing = currentLocation.bearingTo(destination);
        float distance = currentLocation.distanceTo(destination);
        Log.e("DEBUG-GPS", "CurrentLocation: " + currentLocation.getLongitude() + " / " + currentLocation.getLatitude
                ());
        Log.e("DEBUG-GPS", "Destination: " + destination.getLongitude() + " / " + destination.getLatitude());
        Log.e("DEBUG", "Bearing: " + bearing + " Distance: " + distance);
        for (IGPSLocationListener listener : listeners) {
            listener.onLocationChanged(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }
}
