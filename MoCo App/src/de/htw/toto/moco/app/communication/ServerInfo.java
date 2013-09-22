package de.htw.toto.moco.app.communication;

import com.google.gson.Gson;
import de.htw.toto.moco.server.game.rpssl.GameHand;
import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.tools.HexStringConverter;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */
public class ServerInfo {
    private static final String DELIMETER       = "/";
    private static final String MESSAGE_BASE    = "message";
    private static final String LOGIN_BASE      = "login";
    private static final String LIST_BASE       = "list";
    private static final String POI_BASE        = "poi";
    private static final String DETAILS         = "details";
    private static final String REGISTER        = "register";
    private static final String USER            = "user";
    private static final String ADD_FRIEND      = "addFriend";
    private static final String ADD_MESSAGE     = "addMessage";
    private static final String INVITE_STATE    = "inviteState";
    private static final String POLL_GAME_STATE = "pollGameState";
    private static final String PLAY_HAND       = "playHand";
    private static final String RPSSL           = "rpssl";
    private static final String GET_RESULT      = "getResult";
    private static final String GAME            = "game";

    static {
        serverBaseURL = "http://192.168.2.121:9998/";
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
        String hexedJSON = "";
        try {
            hexedJSON = HexStringConverter.getHexStringConverterInstance().stringToHex(jsonMessage);
        }
        catch (UnsupportedEncodingException e) {

        }
        return serverBaseURL + MESSAGE_BASE + DELIMETER + ADD_MESSAGE + DELIMETER + SessionInfo.getInstance().getToken() +
                DELIMETER + hexedJSON;
    }

    public static String getPOIListURL() {
        return serverBaseURL + POI_BASE + DELIMETER + LIST_BASE + DELIMETER + SessionInfo.getInstance().getToken();
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

    public static String getGameInfoListURL() {
        return serverBaseURL + GAME + DELIMETER + LIST_BASE + DELIMETER + SessionInfo.getInstance().getToken();
    }

    public static String getReadyForRPSSLURL() {
        return serverBaseURL + GAME + DELIMETER + RPSSL + DELIMETER + SessionInfo.getInstance().getToken() +
                DELIMETER + SessionInfo.getInstance().getUsername();
    }

    public static String getCheckInviteStateURL() {
        return serverBaseURL + GAME + DELIMETER + RPSSL + DELIMETER + INVITE_STATE + DELIMETER + SessionInfo.getInstance().getToken()
                + DELIMETER + SessionInfo.getInstance().getUsername();
    }

    public static String getPollGameStateURL(int gameID) {
        return serverBaseURL + GAME + DELIMETER + RPSSL + DELIMETER + POLL_GAME_STATE + DELIMETER + SessionInfo.getInstance().getToken()
                + DELIMETER + SessionInfo.getInstance().getUsername() + DELIMETER + gameID;
    }

    public static String getPlayHandURL(int gameID, GameHand gameHand) {
        return serverBaseURL + GAME + DELIMETER + RPSSL + DELIMETER + PLAY_HAND + DELIMETER + SessionInfo.getInstance().getToken()
                + DELIMETER + SessionInfo.getInstance().getUsername() + DELIMETER + gameID + DELIMETER + gameHand.name();
    }

    public static String getGameResultURL(int gameID) {
        return serverBaseURL + GAME + DELIMETER + RPSSL + DELIMETER + GET_RESULT + DELIMETER + SessionInfo.getInstance().getToken()
                + DELIMETER + SessionInfo.getInstance().getUsername() + DELIMETER + gameID;
    }
}
