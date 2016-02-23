package com.moodleapp2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Coursesoverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursesoverview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //set here the property after taking them from the mapp and json file.
        setSupportActionBar(toolbar);



    }
}
