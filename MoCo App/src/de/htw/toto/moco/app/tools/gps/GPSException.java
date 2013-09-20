package de.htw.toto.moco.app.tools.gps;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public class GPSException extends Exception {
    public GPSException() {
    }

    public GPSException(String detailMessage) {
        super(detailMessage);
    }

    public GPSException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public GPSException(Throwable throwable) {
        super(throwable);
    }
}
