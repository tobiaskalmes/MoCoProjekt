package de.htw.toto.moco.server.tools;

import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 11:52
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

    public static ChatMessage parseToChatMessage(String jsonString) {
        ChatMessage cm = new ChatMessage();
        try {
            JSONObject baseObject = new JSONObject(jsonString);
            cm.setSender(baseObject.get("sender").toString());
            cm.setReceiver(baseObject.get("receiver").toString());
            cm.setContent(baseObject.get("content").toString());
            cm.setId(Integer.parseInt(baseObject.get("id").toString()));
            cm.setSendTime(Long.parseLong(baseObject.get("sendTime").toString()));
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
