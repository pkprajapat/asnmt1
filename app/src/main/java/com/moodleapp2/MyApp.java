package com.moodleapp2;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.ProgressDialog;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Himanshu Goyal on 18-02-2016.
 */
public class MyApp extends Application {
    public String urllogin = "http://10.206.160.12:8000/default/login.json?userid=vinay&password=vinay";
    public JSONObject login;
    public String urlcourse = "http://10.206.160.12:8000/courses/list.json";
    public JSONObject course;
    public String urlnotifctn = "http://10.206.160.12:8000/default/notifications.json";
    public JSONObject notifctn;
    public String urlgrade = "http://10.206.160.12:8000/default/grades.json";
    public JSONObject grade;
    public String urlasnmt  = "http://10.206.160.12:8000/courses/course.json/<coursecode>/assignments";
    public JSONObject asnmt;
    public String urlspcfcasnmt = "http://10.206.160.12:8000/courses/assignment.json/<assignment‐number>== 1";
    public JSONObject spcfasnmt;
    public String urlcrsdata= "http://10.206.160.12:8000/courses/course.json/<Coursecode>";//will return
    public JSONObject crsdata;
    public String urlcrsgrade= "http://10.206.160.12:8000/courses/course.json/cop290/grades";
    public JSONObject crsgrade;
    public String urlcrsthread = "http://10.206.160.12:8000/courses/course.json/<coursecode>/thread";
    public JSONObject crsthread;
    public String urlparthread= "http://10.206.160.12:8000/threads/thread.json/<thread‐number";
    public JSONObject parthread;
    public String urlnewthread = "http://10.206.160.12:8000/threads/new.json?title=<title>&description=<desc>&course_code=<coursecode>";
    public JSONObject newthread;
    public String cmntTothtread = "http://10.206.160.12:8000/threads/post_comment.json?thread_id=<thread_id>&description=<desc>";
    public JSONObject tothread;
    public String logout = "http://10.206.160.12:8000/default/logout.json";
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "sessionid";


    public loginstore lognobj = new loginstore();
    private static MyApp _instance;
    private RequestQueue _requestQueue;
    private SharedPreferences _preferences;
   static ProgressDialog pDialog;
    public static JSONObject parsing;
    public String parse;
    public static void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public  void toaster(String p){
        Toast.makeText(getApplicationContext(), p , Toast.LENGTH_LONG).show();

    }
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

    public final void reset(){
        SharedPreferences.Editor prefEditor = _preferences.edit();
        prefEditor.remove(SESSION_COOKIE);
        prefEditor.commit();

    }




}