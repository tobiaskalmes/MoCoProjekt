package de.htw.toto.moco.app.communication.poi;

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
 * Date: 17.09.13
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class POIRequester {
    private POIRequester() {
    }

    public static void requestPOIList(Context context, final IPOIListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getPOIListURL(),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          //TODO:build list
                                                          listener.result(null);
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
