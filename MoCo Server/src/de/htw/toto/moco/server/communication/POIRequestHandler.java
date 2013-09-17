package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Path("/poi")
public class POIRequestHandler extends RequestHandler {
    @GET
    @Path(value = "/list")
    @Produces(MediaType.TEXT_XML)
    public POIList getPOIList() {
        //TODO: get list from DB

        POIList pois = new POIList();
        List<POI> pl = new ArrayList<POI>();
        pl.add(new POI(13.0, 14.5, "poiname", false, -1, 1234));
        pois.setPois(pl);
        return pois;
    }

    @GET
    @Path(value = "/details/{idPOI}")
    @Produces(MediaType.TEXT_XML)
    public POIRequestHandler getPOIDetails(@PathParam(value = "idPOI") int idPOI) {
        //TODO: get details from db
        return null;
    }
}
