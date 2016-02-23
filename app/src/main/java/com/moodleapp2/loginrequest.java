package com.moodleapp2;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import android.app.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Himanshu Goyal on 20-02-2016.
 */
public class loginrequest {

     private MyApp mapp = MyApp.get();
//it is just ot use some genral functunallity in myapp which is only assesible in application class
     public loginrequest(String urltaker) {

        MyApp.showpDialog();
//willchange this it is actually a string request in the next class
        StringRequest strObjReq = new StringRequest(Request.Method.GET,
                urltaker, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //mapp.parse = response;
                Log.d("tag",  response.toString());
                //           txtrespo.setText(response);
                otherwork();

                try {
                   JSONObject obj = new JSONObject(response);
                   mapp.login = obj;
                   Log.d("tag",obj.getString("first_name"));
                   Log.d("tag", obj.toString());
                   //loginstore t = mapp.lognobj;
                   /*t.emailid = obj.getString("email");
                   t.entryno = obj.getString("entry_no");
                   t.lname = obj.getString("last_name");
                   t.fname = obj.getString("first_name");
                   t.type = obj.getInt("type_");
                   t.success = obj.getBoolean("success");
                   Log.d("tag", t.lname);
                   Log.d("tag",obj.getString("first_name"));
                   Log.d("tag","sahi to hain lakin de nahi rha" );
                   if (obj.getBoolean("success")) Log.d("tag","sahi to hain lakin de nahi rha" );
                   if(t.success){Log.d("tag", "it is changing");
                   }*/
                   //sending all the data to be used later by other activity after login

                   // extract data from here..
               }
                catch (JSONException e){}

                MyApp.hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
               Log.d("tag"," Error: in volley" + error.toString());
                mapp.toaster(error.toString());
                // hide the progress dialog
               MyApp.hidepDialog();
            }
        });

        // Adding request to request queue
        MyApp.get().getRequestQueue().add(strObjReq); //add the above request in the instance of my app which get the
        //the exsisting queue to us and we add our request to it.
        //
      }
     public void otherwork(){
    otherrequests coursereq = new otherrequests(mapp.urlcourse);
    otherrequests gradereq  = new otherrequests(mapp.urlgrade);
    otherrequests notireq = new otherrequests(mapp.urlnotifctn);
}

}
