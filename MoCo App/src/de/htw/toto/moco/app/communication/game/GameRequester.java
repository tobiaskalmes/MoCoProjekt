package de.htw.toto.moco.app.communication.game;

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
 * Date: 21.09.13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
public class GameRequester {
    private GameRequester() {
    }

    public static void requestGameInfos(Context context, final IGameInfoListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getGameInfoListURL(),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.result(
                                                                  JSONParser.parseToGameInfoList(s));
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
