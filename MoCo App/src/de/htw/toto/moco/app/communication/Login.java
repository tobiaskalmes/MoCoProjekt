package de.htw.toto.moco.app.communication;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 14.09.13
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class Login {
    private static final String LOGIN_BASE_URL = "http://192.168.2.102:9998/login/";
    private static final String DELIMETER      = "/";
    private static Login instance;

    private Login() {
    }

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public void login(Context context, final ILoginListener listener, String username, String passwordHash) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestURL = LOGIN_BASE_URL + username + DELIMETER + passwordHash;
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
                                                          listener.result(volleyError.getMessage());
                                                      }
                                                  }
        );
        queue.add(request);
    }

}
