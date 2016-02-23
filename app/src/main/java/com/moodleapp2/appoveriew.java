package com.moodleapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class appoveriew extends AppCompatActivity {
    MyApp mapp = MyApp.get();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoveriew);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //here also write the code for using the request tha ti have got from the login thta is name and other stuff.

        JSONObject obj = mapp.login;

       /* try{
            loginstore t = mapp.lognobj;
                   t.emailid = obj.getString("email");
                   t.entryno = obj.getString("entry_no");
                   t.lname = obj.getString("last_name");
                   t.fname = obj.getString("first_name");
                   t.type = obj.getInt("type_");
                   t.success = obj.getBoolean("success");
              Log.d("tag","before name ");
              Log.d("tag", t.lname);
              Log.d("tag","after name ");
              Log.d("tag",obj.getString("first_name"));
              Log.d("tag","sahi to hain lakin de nahi rha" );

            //String name = data.getString()
        }
        catch(JSONException e){}
        */
        Button grade = (Button)findViewById(R.id.grade);
        Button course = (Button)findViewById(R.id.course);
        Button notification = (Button)findViewById(R.id.noti);

        grade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //have fetched the data jus tafter the login request will access that at the time of opening activity in that
                //activity in oncreate method
                Intent i = new Intent(getApplicationContext(), Gradesoverview.class);
                appoveriew.this.startActivity(i);
            }
        });
        course.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //new otherrequests(mapp.urlcourse);
                Intent i = new Intent(getApplicationContext(), Coursesoverview.class);
                appoveriew.this.startActivity(i);

            }
        });
        notification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Notioverview.class);
                appoveriew.this.startActivity(i);

            }
        });

    }

}
