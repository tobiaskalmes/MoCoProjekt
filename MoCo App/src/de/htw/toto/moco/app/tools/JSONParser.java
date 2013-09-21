package de.htw.toto.moco.app.tools;

import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;
import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class JSONParser {
    private JSONParser() {
    }

    public static ChatMessageList parseToChatMessageList(String jsonString) {
        ChatMessageList cml = new ChatMessageList();
        try {
            JSONObject baseObject = new JSONObject(jsonString);
            JSONArray messages = baseObject.getJSONArray("messages");
            int messageCount = messages.length();
            for (int i = 0; i < messageCount; ++i) {
                ChatMessage message = new ChatMessage();
                JSONObject messageObject = messages.getJSONObject(i).getJSONObject("ChatMessage");
                message.setSender(messageObject.get("sender").toString());
                message.setReceiver(messageObject.get("receiver").toString());
                message.setContent(messageObject.get("content").toString());
                message.setId(Integer.parseInt(messageObject.get("id").toString()));
                message.setSendTime(Long.parseLong(messageObject.get("sendTime").toString()));
                cml.getMessages().add(message);
            }
        }
        catch (JSONException e) {
        }
        return cml;
    }

    public static POIList parseToPOIList(String jsonString) {
        POIList pl = new POIList();

        try {
            JSONObject baseObject = new JSONObject(jsonString);
            JSONArray pois = baseObject.getJSONArray("pois");
            int poiCount = pois.length();
            for (int i = 0; i < poiCount; ++i) {
                POI poi = new POI();
                JSONObject poiObject = pois.getJSONObject(i).getJSONObject("POI");
                poi.setActive(poiObject.getBoolean("active"));
                poi.setIdPoi(poiObject.getInt("idPoi"));
                poi.setLatitude(poiObject.getDouble("latitude"));
                poi.setLongitude(poiObject.getDouble("longitude"));
                poi.setType(poiObject.getInt("type"));
                poi.setName(poiObject.getString("name"));
                pl.getPois().add(poi);
            }
        }
        catch (JSONException e) {

        }
        return pl;
    }

    public static ChatMessage parseToChatMessage(String jsonString) {
        ChatMessage cm = new ChatMessage();
        try {
            JSONObject baseObject = new JSONObject(jsonString);
            JSONObject messageObject = baseObject.getJSONObject("ChatMessage");
            cm.setSender(messageObject.get("sender").toString());
            cm.setReceiver(messageObject.get("receiver").toString());
            cm.setContent(messageObject.get("content").toString());
            cm.setId(Integer.parseInt(messageObject.get("id").toString()));
            cm.setSendTime(Long.parseLong(messageObject.get("sendTime").toString()));
        }
        catch (JSONException e) {
        }
        return cm;
    }

    public static List<String> parseToUserList(String jsonString) {
        List<String> userList = new ArrayList<String>();
        try {
            JSONObject baseObject = new JSONObject(jsonString);
            JSONArray users = baseObject.getJSONArray("users");
            int userCount = users.length();
            for (int i = 0; i < userCount; ++i) {
                userList.add(users.get(i).toString());
            }
        }
        catch (JSONException e) {
        }
        return userList;
    }
}
