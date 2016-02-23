package com.moodleapp2;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Himanshu Goyal on 20-02-2016.
 */
public class otherrequests {
    // public static JSONObject obj ;
   private MyApp mapp =  MyApp.get();

   public  otherrequests(final String urltaker) {
        MyApp.showpDialog();
//willchange this it is actually a string request in the next class
        jsonreal realjsonObjReq = new jsonreal(Request.Method.GET,
                urltaker, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("tag", response.toString());
                //           txtrespo.setText(response);
                //  String t = response.getString("current_year");
                   // Log.d("tag", "lol " + t);
                  //  txtrespo.setText((response.getString("current_year")));

                //}
                //catch (JSONException e){Log.d("tag","JSON Exception" + e.toString());
                //}
               if(urltaker.equals(mapp.urlcourse) ) {allcourse(MyApp.parsing);
                   mapp.course = response;
               }
               if (urltaker == mapp.urlgrade) {allgrade(MyApp.parsing);
               mapp.grade = response;
               }
               if(urltaker == mapp.urlnotifctn) {
                   mapp.notifctn = response;
                   allnotif(MyApp.parsing);
               }
                MyApp.hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", " Error: in volley" + error.toString());
               mapp.toaster(error.toString());
                // hide the progress dialog
                MyApp.hidepDialog();
            }
        });

        // Adding request to request queue
        MyApp.get().getRequestQueue().add(realjsonObjReq); //add the above request in the instance of my app which get the
        //the exsisting queue to us and we add our request to it.
    }
public void allcourse(JSONObject res){
 //set whatever need to be set in the courses activity


    try{
        Log.i("tag", "before trying and the parsing is in volley " + MyApp.parsing.toString());
        JSONArray a = res.getJSONArray("grades");
        Log.i("tag", "after trying " + a.toString());
    }
    catch(JSONException e) {
        Log.i("tag", "volley is not workingMeans some error in myapp.get function is not doing what i excpected" + e.toString());
    }

}
    public void allnotif(JSONObject res){
        //set here

    }

    public void allgrade(JSONObject res){}



}
