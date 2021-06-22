package com.marichtech.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //private static String TAG = "Mainactivity";

    RequestQueue rq;
    String api = "https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyDjYNpUP2IuNd5fHDaugtiKf1molBrTF9U&address=chicago";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rq = Volley.newRequestQueue(this);

        volleyGet(api);
    }


    public void volleyGet(String URl) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("JsonResponse", response.toString());

                //Obtaining values from json array
                try{
                    JSONArray officesArray = response.getJSONArray("offices");

                    for(int i=0; i< officesArray.length(); i++){

                        JSONObject jsonObject = officesArray.getJSONObject(i);

                        Log.e("Office Name: ", jsonObject.getString("name"));
                        JSONArray officialIndices  =jsonObject.getJSONArray("officialIndices");

                        Log.e( "officialIndices",officialIndices.toString());


                    }

                }catch (Exception e ){
                    Log.e("Exception", e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.toString());

            }
        });

        requestQueue.add(jsonObjectRequest);


    }

    /**
     * JsonObject({})  ->  JsonArray[offices]  -> JsonObject(name) -> JsonArray(indices)
     */


}