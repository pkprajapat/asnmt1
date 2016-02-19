package com.moodleapp2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.moodleapp2.R;
import com.moodleapp2.MyApp;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
import com.moodleapp2.JsonObjectRequest;

import java.util.Map;

public class MainActivity extends AppCompatActivity {


    // json object response url
    private String urlJsonObj = "http://127.0.0.1:8000/default/login.json?";

    // json array response url
    //  private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";

    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;
    private TextView txtrespo;
    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMakeObjectRequest = (Button) findViewById(R.id.login);
        txtResponse = (TextView) findViewById(R.id.text);
        txtrespo = (TextView) findViewById(R.id.headr);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                String userid = ((TextView)findViewById(R.id.uname)).toString();
                String pass = ((TextView)findViewById(R.id.pass)).toString();
                urlJsonObj = urlJsonObj+"userid="+userid + "&password="+pass;
                makeJsonObjectRequest();
            }
        });


    }
    //Map<String, String> params;
    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                          urlJsonObj,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                txtrespo.setText(response.toString());
                try {
                    // Parsing json object response
                    // response will be a json object
                    boolean success = response.getBoolean("success");
                    JSONObject user = response.getJSONObject("user");
                    String email = user.getString("email");
                    String name = user.getString("last_name");
                    int type = user.getInt("type_");//1  for prof. and  0 for student

                    jsonResponse = "";
                    jsonResponse += "last_name: " + name + "\n\n";
                    jsonResponse += "Email: " + email + "\n\n";
                    jsonResponse += "type: " + type + "\n\n";
                    //jsonResponse += "Mobile: " + mobile + "\n\n";
                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                   hidepDialog();
            }
        })
        {
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                // since we don't know which of the two underlying network vehicles
                // will Volley use, we have to handle and store session cookies manually
                //  MyApp.get().checkSessionCookie(response.headers);
                 Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_LONG).show();
                 // MainActivity.
                return super.parseNetworkResponse(response);
            }

        }


                ;

        // Adding request to request queue
        MyApp.get().getRequestQueue().add(jsonObjReq); //add the above request in the instance of my app which get the
        //the exsisting queue to us and we add our request to it.
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
