package de.htw.toto.moco.app.tools;

import android.util.Log;
import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 18.09.13
 * Time: 13:51
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
            Log.e("JSONParser", "Parser Exception", e);
        }
        return cml;
    }
}
