package de.htw.toto.moco.app.communication.register;

import android.content.Context;
import android.util.Log;
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
 * Date: 18.09.13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public class RegisterRequester {
    private RegisterRequester() {
    }

    public static void register(Context context, final IRegisterListener listener, String username,
                                String passwordHash) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestURL = ServerInfo.serverBaseURL + ServerInfo.REGISTER + ServerInfo.DELIMETER + username + ServerInfo
                .DELIMETER + passwordHash;
        Log.e("DEBUG", requestURL);
        StringRequest request = new StringRequest(Request.Method.GET, requestURL,
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.registerResult(s);
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
