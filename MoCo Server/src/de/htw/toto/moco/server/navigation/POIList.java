package de.htw.toto.moco.server.navigation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "POIList")
public class POIList {
    private List<POI> pois;

    public POIList() {
        pois = new ArrayList<POI>();
    }

    @XmlElements({@XmlElement(name = "POI", type = POI.class)})
    @XmlElementWrapper
    public List<POI> getPois() {
        return pois;
    }

    public void setPois(List<POI> pois) {
        this.pois = pois;
    }
}
