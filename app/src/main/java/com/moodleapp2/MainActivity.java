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
import android.widget.EditText;
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
    private String urlJsonObj = "http://10.206.160.12:8000/default/login.json?userid=vinay&password=vinay";
    private String urlcourse = "http://10.206.160.12:8000/courses/list.json";
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
        //txtrespo = (TextView) findViewById(R.id.headr);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                Log.i("tag", "got it " + urlJsonObj);
                makeJsonObjectRequest(urlJsonObj);
                Log.i("tag", " afterjsonreq in create");

            }
        });
        Button course = (Button)findViewById(R.id.course);
        course.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                Log.i("tag","got it " + urlcourse);
                makerealJsonObject(urlcourse);
                Log.i("tag", " after course jsonreq in create" );

            }
        });



    }

//make rela json and other make the stringobject
    private void makerealJsonObject(String urltaker) {

        showpDialog();
//willchange this it is actually a string request in the next class
        jsonreal realjsonObjReq = new jsonreal(Request.Method.GET,
                urltaker, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("tag",TAG+ response.toString());
                //           txtrespo.setText(response);
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", TAG+" Error: in volley" + error.toString());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        MyApp.get().getRequestQueue().add(realjsonObjReq); //add the above request in the instance of my app which get the
        //the exsisting queue to us and we add our request to it.
    }
    //Map<String, String> params;
    private void makeJsonObjectRequest(String urltaker) {

        showpDialog();
//willchange this it is actually a string request in the next class
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                          urltaker, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("tag",TAG+ response.toString());
     //           txtrespo.setText(response);
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", TAG+" Error: in volley" + error.toString());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hide the progress dialog
                   hidepDialog();
            }
        });

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
