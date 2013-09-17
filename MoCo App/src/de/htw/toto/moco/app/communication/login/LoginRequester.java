package de.htw.toto.moco.app.communication.login;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import de.htw.toto.moco.app.communication.ServerInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 14.09.13
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class LoginRequester {
    private LoginRequester() {
    }

    public static void login(Context context, final ILoginListener listener, String username, String passwordHash) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestURL = ServerInfo.serverBaseURL + username + ServerInfo.DELIMETER + passwordHash;
        StringRequest request = new StringRequest(Request.Method.GET, requestURL,
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.result(s);
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
