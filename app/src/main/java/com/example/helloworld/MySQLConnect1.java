package com.example.helloworld;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MySQLConnect1 {

    private final Activity main;
    private List<String> list;
    private String URL1 = "http://unhurt-beans.000webhostapp.com/", GET_URL = "testphp/get_post1.php";

    public MySQLConnect1(){
        main = null;
    }

    public MySQLConnect1(Activity mainA){
        main = mainA;
        list = new ArrayList<String>();
    }

    public List<String> getData(){

        String url = URL1 + GET_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
                Toast.makeText(main, list.get(0), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);

        return list;
    }

    public void showJSON(String response){
        String Temperature = "";
        String Moisture = "";
        String Dust1_0 = "";
        String Dust2_5 = "";
        String Dust10_0 = "";


        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++){
                JSONObject collectData = result.getJSONObject(i);
                Temperature = collectData.getString("Temperature");
                list.add(Temperature);

                Moisture = collectData.getString("Moisture");
                list.add(Moisture);

                Dust1_0 = collectData.getString("Dust1_0");
                list.add(Dust1_0);

                Dust2_5 = collectData.getString("Dust2_5");
                list.add(Dust2_5);

                Dust10_0 = collectData.getString("Dust10_0");
                list.add(Dust10_0);

            }

        }catch (JSONException ex){ ex.printStackTrace();}
    }

}

