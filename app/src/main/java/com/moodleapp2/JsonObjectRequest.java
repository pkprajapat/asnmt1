package com.moodleapp2;

/**
 * Created by Himanshu Goyal on 18-02-2016.
 */

import android.content.Context;
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


public class JsonObjectRequest extends com.android.volley.toolbox.JsonObjectRequest {

    private final Map<String, String> _params;

    /**
     * @param method
     * @param url
     * @param params
     *            A {@link HashMap} to post with the request. Null is allowed
     *            and indicates no parameters will be posted along with request.
     * @param listener
     * @param errorListener
     */
    public JsonObjectRequest(int method, String url, Map<String, String> params, Response.Listener<JSONObject> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);

        _params = params;
    }

    @Override
    protected Map<String, String> getParams() {
        return _params;
    }

    /* (non-Javadoc)
     * @see com.android.volley.toolbox.StringRequest#parseNetworkResponse(com.android.volley.NetworkResponse)
     */
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        // since we don't know which of the two underlying network vehicles
        // will Volley use, we have to handle and store session cookies manually
        //  MyApp.get().checkSessionCookie(response.headers);
        // Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show();
//        MainActivity.

        return super.parseNetworkResponse(response);
    }



    /* (non-Javadoc)
     * @see com.android.volley.Request#getHeaders()
     */
    /*@Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        MyApp.get().addSessionCookie(headers);

        return headers;
    }*/
}