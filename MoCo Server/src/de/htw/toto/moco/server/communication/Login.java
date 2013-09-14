package de.htw.toto.moco.server.communication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 14.09.13
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */

@Path("/login")
public class Login {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String test()
    {
        return "<html><body>test</body></html>";
    }
}
