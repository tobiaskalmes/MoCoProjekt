package de.htw.toto.moco.server.communication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Path("/poi")
public class POI {
    @GET
    @Path(value = "/list")
    @Produces(MediaType.TEXT_XML)
    public List<POI> getPOIList() {
        //TODO: get list from DB
        return null;
    }

    @GET
    @Path(value = "/details/{idPOI}")
    @Produces(MediaType.TEXT_XML)
    public POI getPOIDetails(int idPOI) {
        //TODO: get details from db
        return null;
    }
}
