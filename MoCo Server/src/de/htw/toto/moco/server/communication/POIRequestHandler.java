package de.htw.toto.moco.server.communication;

import de.htw.toto.moco.server.database.DBBackend;
import de.htw.toto.moco.server.navigation.POIList;
import de.htw.toto.moco.server.token.TokenHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    @Path("/list/{token}")
    @Produces(MediaType.TEXT_XML)
    public POIList getPOIList(@PathParam("token") String token) {
        if (!TokenHandler.getInstance().checkToken(token)) {
            logger.log("Token " + token + " is not valid!", Level.WARNING);
            return null;
        }
        logger.log("Fetched POIs fro token: " + token, Level.INFO);
        return DBBackend.getInstance().getAllPOIs();
    }
}
