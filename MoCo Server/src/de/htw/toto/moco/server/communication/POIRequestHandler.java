package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;
import de.htw.toto.moco.server.token.TokenHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
    @Path(value = "/list/{token}")
    @Produces(MediaType.TEXT_XML)
    public POIList getPOIList(@PathParam(value = "token") String token) {
        //TODO: get list from DB
        if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return null;
        }
        POIList pois = new POIList();
        List<POI> pl = new ArrayList<POI>();
        pl.add(new POI(13.0, 14.5, "poiname", false, -1, 1234));
        pois.setPois(pl);
        return pois;
    }

    @GET
    @Path(value = "/details/{token}/{idPOI}")
    @Produces(MediaType.TEXT_XML)
    public POI getPOIDetails(@PathParam(value = "idPOI") int idPOI,
                             @PathParam(value = "token") String token) {
        if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return null;
        }
        //TODO: get details from db
        return null;
    }
}
