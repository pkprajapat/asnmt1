package com.moodleapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;


//import com.android.volley.toolbox.JsonObjectRequest;
//import com.moodleapp2.JsonObjectRequest;


public class MainActivity extends AppCompatActivity {

    MyApp mapp = MyApp.get();
    // json object response url

    // json array response url
    //  private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";

    private static String TAG = MainActivity.class.getSimpleName();

    private Button btnlogin;

    // Progress dialog
    private ProgressDialog pDialog1;

    private TextView txtResponse;
     TextView txtrespo;
    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin = (Button) findViewById(R.id.pop);
//        txtResponse = (TextView) findViewById(R.id.text);
        txtrespo = (TextView) findViewById(R.id.ret);
        MyApp.pDialog = new ProgressDialog(this);
        pDialog1 = MyApp.pDialog;
        pDialog1.setMessage("Please wait...");
        pDialog1.setCancelable(false);

        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                MyApp.get().reset();
                Log.i("tag", "got it " + mapp.urllogin);
                new loginrequest(mapp.urllogin);
                /*otherrequests coursereq = new otherrequests(mapp.urlcourse);
                try {
                    String t = mapp.parsing.getString("current_year");
//                    Log.i("tag", " afterjsonreq in create");
                    txtrespo.setText(t);

                } catch (JSONException e){}
                */
                //set the content of the activity from myap.loginobj which has lal the information from the login
                //if(mapp.lognobj.success) {
                    Intent i = new Intent(getApplicationContext(), appoveriew.class);
                    MainActivity.this.startActivity(i);
                /*    Log.i("tag", " afterjsonreq in create");
                }
                else{
                    mapp.toaster("Inavlid user-name or password");


                }*/
            }
        });
        /*Button course = (Button)findViewById(R.id.Login);
        course.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                Log.i("tag", "got it " + mapp.urlcourse);
                new otherrequests(mapp.urlcourse);
                Log.i("tag", " after course jsonreq in create");

            }
        });*/



    }

//make rela json and other make the stringobject
    //Map<String, String> params;





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
