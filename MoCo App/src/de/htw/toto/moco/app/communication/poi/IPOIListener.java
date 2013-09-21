package de.htw.toto.moco.app.communication.poi;

import de.htw.toto.moco.server.navigation.POIList;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public interface IPOIListener {
    public void result(POIList result);

    public void error(Throwable e);
}
