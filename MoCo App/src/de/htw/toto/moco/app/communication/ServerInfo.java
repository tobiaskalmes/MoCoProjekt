package de.htw.toto.moco.app.communication;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */
public class ServerInfo {
    public static final String DELIMETER    = "/";
    public static final String MESSAGE_BASE = "message";
    public static final String LOGIN_BASE   = "login";
    public static final String LIST_BASE    = "list";
    public static final String POI_BASE     = "poi";
    public static final String DETAILS      = "details";

    static {
        serverBaseURL = "http://192.168.2.102:9998/";
    }

    public static String serverBaseURL;

    private ServerInfo() {
    }

}
