package de.htw.toto.moco.app.communication;

import com.google.gson.Gson;
import de.htw.toto.moco.server.messaging.ChatMessage;

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
    public static final String REGISTER     = "register";
    public static final String USER         = "user";
    public static final String ADD_FRIEND   = "addFriend";

    static {
        serverBaseURL = "http://192.168.2.102:9998/";
    }

    public static String serverBaseURL;

    private ServerInfo() {
    }

    public static String getLoginURL(String username, String passwordHash) {
        return serverBaseURL + LOGIN_BASE + DELIMETER + username + DELIMETER + passwordHash;
    }

    public static String getChatMessageListURL(String chatPartner) {
        return serverBaseURL + MESSAGE_BASE + DELIMETER + LIST_BASE + DELIMETER + SessionInfo.getInstance().getToken() + DELIMETER + SessionInfo.getInstance().getUsername() + DELIMETER + chatPartner;
    }

    public static String getNewChatMessageURL(String chatPartner, String content) {
        Gson gson = new Gson();
        ChatMessage cm = new ChatMessage();
        cm.setSender(SessionInfo.getInstance().getUsername());
        cm.setReceiver(chatPartner);
        cm.setContent(content);
        String jsonMessage = gson.toJson(cm);
        return serverBaseURL + SessionInfo.getInstance().getToken() + DELIMETER + jsonMessage;
    }

    public static String getPOIListURL() {
        //TODO: add GPS coordinates
        return serverBaseURL + LIST_BASE + DELIMETER + SessionInfo.getInstance().getToken();
    }

    public static String getRegisterURL(String username, String passwordHash) {
        return serverBaseURL + REGISTER + DELIMETER + username + DELIMETER + passwordHash;
    }

    public static String getUserListURL() {
        return serverBaseURL + USER + DELIMETER + LIST_BASE + DELIMETER + SessionInfo.getInstance().getToken()
                + DELIMETER + SessionInfo.getInstance().getUsername();
    }

    public static String getAddFriendURL(String friend) {
        return serverBaseURL + USER + DELIMETER + ADD_FRIEND + DELIMETER + SessionInfo.getInstance().getToken()
                + DELIMETER + SessionInfo.getInstance().getUsername() + DELIMETER + friend;
    }
}
