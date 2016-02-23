package com.moodleapp2;

/**
 * Created by Himanshu Goyal on 18-02-2016.
 */

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.moodleapp2.R;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
//import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class StringRequest extends com.android.volley.toolbox.StringRequest {

    //private final Map<String, String> _params;

    /**
     * @param method
     * @param url
    //* @param params
     *            A {@link HashMap} to post with the request. Null is allowed
     *            and indicates no parameters will be posted along with request.
     * @param listener
     * @param errorListener
     */
    public StringRequest(int method, String url, Response.Listener<String> listener,
                             Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        Log.d("tag", "in JSON object request class after cons.");


        //_params = params;
    }

    /* (non-Javadoc)
     * @see com.android.volley.toolbox.StringRequest#parseNetworkResponse(com.android.volley.NetworkResponse)
     */
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // since we don't know which of the two underlying network vehicles
        // will Volley use, we have to handle and store session cookies manually
        MyApp.get().checkSessionCookie(response.headers);
        // Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show();
//        MainActivity.

        Log.d("tag", "in networkrespo");
        Response<String> med = super.parseNetworkResponse(response);
        Log.d("tag", "network"+response.headers );
        return med;
    }



    /* (non-Javadoc)
     * @see com.android.volley.Request#getHeaders()
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        MyApp.get().addSessionCookie(headers);

        return headers;
    }
}