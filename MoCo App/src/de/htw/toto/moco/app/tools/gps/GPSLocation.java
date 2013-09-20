package de.htw.toto.moco.app.tools.gps;

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
    private        NavigationInfo             navInfo;
    private        List<IGPSLocationListener> gpsListeners;
    private        List<INavigationListener>  navListeners;


    private GPSLocation() {
        gpsListeners = new ArrayList<IGPSLocationListener>();
        navListeners = new ArrayList<INavigationListener>();
    }

    public static GPSLocation getInstance() {
        if (instance == null) {
            instance = new GPSLocation();
        }
        return instance;
    }

    public void navigateTo(double latitude, double longitude) throws GPSException {
        if (currentLocation == null) {
            throw new GPSException("No GPS Signal");
        }
        Location destination = new Location(currentLocation);
        destination.setLatitude(latitude);
        destination.setLongitude(longitude);
        navInfo = new NavigationInfo(destination);
    }

    public void addGPSListener(IGPSLocationListener listener) {
        gpsListeners.add(listener);
    }

    public void removeGPSListener(IGPSLocationListener listener) {
        gpsListeners.remove(listener);
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
        for (IGPSLocationListener listener : gpsListeners) {
            listener.onLocationChanged(currentLocation);
        }
        Log.e("DEBUG-GPS",
              "CurrentLocation: " + currentLocation.getLongitude() + " / " + currentLocation.getLatitude());
        if (navInfo != null) {
            navInfo.update(currentLocation);
            for (INavigationListener listener : navListeners) {
                listener.onLocationChanged(navInfo);
            }
            Log.e("DEBUG-GPS",
                  "Destination: " + navInfo.getDestination().getLongitude() + " / " + navInfo.getDestination().getLatitude());
            Log.e("DEBUG", "Bearing: " + navInfo.getBearing() + " Distance: " + navInfo.getDistance());
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

    public void addNavigationListener(INavigationListener listener) {
        navListeners.add(listener);
    }

    public void removeNavigationListener(INavigationListener listener) {
        navListeners.remove(listener);
    }
}
