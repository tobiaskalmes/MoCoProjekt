package de.htw.toto.moco.app.communication.game.RPSSL;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import de.htw.toto.moco.app.communication.ServerInfo;
import de.htw.toto.moco.server.game.GameState;
import de.htw.toto.moco.server.game.rpssl.GameHand;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 22.09.13
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class RPSSLRequester {
    private RPSSLRequester() {
    }

    public static void requestReadyForRPSSL(Context context, final IRPSSLRequester listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getReadyForRPSSLURL(),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.readyForRPSSLResult(s);
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

    public static void requestCheckInviteState(Context context, final IRPSSLRequester listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getCheckInviteStateURL(),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.checkInviteStateResult(Integer.parseInt(s));
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

    public static void requestPollGameState(Context context, final IRPSSLRequester listener, int gameID) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getPollGameStateURL(gameID),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.pollGameStateResult(GameState.valueOf(s));
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

    public static void requestPlayHand(Context context, final IRPSSLRequester listener, int gameID, GameHand gameHand) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getPlayHandURL(gameID, gameHand),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.playHandResult(s);
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

    public static void requestGameResult(Context context, final IRPSSLRequester listener, int gameID) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, ServerInfo.getGameResultURL(gameID),
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String s) {
                                                          listener.gameResult(Integer.parseInt(s));
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
