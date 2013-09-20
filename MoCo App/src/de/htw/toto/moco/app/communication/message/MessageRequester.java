package de.htw.toto.moco.app.communication.message;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import de.htw.toto.moco.app.communication.ServerInfo;
import de.htw.toto.moco.app.tools.JSONParser;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class MessageRequester {
    private MessageRequester() {
    }

    public static void requestChatMessages(Context context, final IMessageListener listener, String chatPartner) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getChatMessageListURL(chatPartner),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.resultChatMessageList(
                                                                  JSONParser.parseToChatMessageList(s));
                                                      }
                                                  },
                                                  new Response.ErrorListener() {
                                                      @Override
                                                      public void onErrorResponse(VolleyError volleyError) {
                                                          listener.error(volleyError);
                                                      }
                                                  }
        );
        queue.add(request);
    }

    public static void requestNewChatMessage(Context context, final IMessageListener listener, String chatPartner,
                                             String content) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET,
                                                  ServerInfo.getNewChatMessageURL(chatPartner, content),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.resultNewChatMessage(
                                                                  (s != null && s.toLowerCase().equals("true")));
                                                      }
                                                  },
                                                  new Response.ErrorListener() {
                                                      @Override
                                                      public void onErrorResponse(VolleyError volleyError) {
                                                          listener.error(volleyError);
                                                      }
                                                  }
        );
        queue.add(request);
    }
}
