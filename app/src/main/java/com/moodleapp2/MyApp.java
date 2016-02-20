package com.moodleapp2;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by Himanshu Goyal on 18-02-2016.
 */
public class MyApp extends Application {
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "sessionid";

    private static MyApp _instance;
    private RequestQueue _requestQueue;
    private SharedPreferences _preferences;


    public static MyApp get() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
        _requestQueue = Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue() {
        return _requestQueue;
    }


    /**
     * Checks the response headers for session cookie and saves it
     * if it finds it.
     * @param headers Response Headers.
     */
    public final void checkSessionCookie(Map<String, String> headers) {
        //if (headers.containsKey(SET_COOKIE_KEY)
                //&& headers.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE)) {
            String cookie = headers.get(SET_COOKIE_KEY);
           // if (cookie.length() > 0) //{
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                SharedPreferences.Editor prefEditor = _preferences.edit();
                prefEditor.putString(SESSION_COOKIE, cookie);
                prefEditor.commit();
                String t = _preferences.getString(SESSION_COOKIE, "");
                Log.d("tag", "cookie " + t + " accepted Session_cookie" );
          //  }
        //}
    }

    /**
     * Adds session cookie to headers if exists.
     * @param headers
     */
    public final void addSessionCookie(Map<String, String> headers) {
        String sessionId = _preferences.getString(SESSION_COOKIE, "");
        Log.d("tag", "cookie " + sessionId + " sending Session_cookie" );

        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            //builder.append(COOKIE_KEY);
            builder.append("session_id_moodleplus=");
            builder.append(sessionId);
            /*if (headers.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(headers.get(COOKIE_KEY));
            }*/
            headers.put(COOKIE_KEY, builder.toString());
            Log.d("tag", "cookie " + headers.toString() + " sending Session_cookie");

        }
    }

}