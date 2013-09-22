package de.htw.toto.moco.server.navigation;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 15.09.13
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlType(propOrder = {"name", "idPoi", "type", "latitude", "longitude", "active"})
public class POI implements Serializable{
    private double latitude, longitude;
    private String  name;
    private boolean active;
    private int     type;
    private Integer idPoi;

    public POI(double latitude, double longitude, String name, boolean active, int type, Integer idPoi){
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.active = active;
        this.type = type;
        this.idPoi = idPoi;
    }

    public POI() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(Integer idPoi) {
        this.idPoi = idPoi;
    }
}
