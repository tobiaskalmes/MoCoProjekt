package de.htw.toto.moco.app.tools.gps;

import android.location.Location;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class NavigationInfo {
    private Location destination;
    private float    bearing;
    private float    distance;

    public NavigationInfo(Location destination) {
        this.destination = destination;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void update(Location location) {
        bearing = location.bearingTo(destination);
        distance = location.distanceTo(destination);
    }
}
