package de.htw.toto.moco.app.communication.user;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import de.htw.toto.moco.app.communication.ServerInfo;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 12:45
 * To change this template use File | Settings | File Templates.
 */
public class UserRequester {
    private UserRequester() {
    }

    public static void requestUserList(Context context, final IUserListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getUserListURL(),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          //TODO: build list
                                                          listener.resultUserList(new ArrayList<String>());
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

    public static void requestAddFriend(Context context, final IUserListener listener, String friend) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET,
                                                  ServerInfo.getAddFriendURL(friend),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.resultAddFriend(
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
