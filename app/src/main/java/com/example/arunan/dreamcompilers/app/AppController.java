package com.example.arunan.dreamcompilers.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arunan on 12/13/16.
 */

//instantiate volley core objects
public class AppController extends AppCompatActivity {
    ArrayList<String> values;
    ArrayList<String> keys;
    String SERVER_URL = "";
    RequestQueue requestQueue;
    String response_msg = "";
    Activity activity;
    final String TAG = "ServerRequest";
    String requestTag;
    ProgressDialog mProgressDialog;

    public AppController(int arg_count, Activity activity){
        values = new ArrayList<String>(arg_count);
        keys = new ArrayList<String>(arg_count);
        this.activity = activity;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setCancelable(true);

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    public void set_server_url(String url){
        SERVER_URL = url;
    }

    public void setParams(String key, String value){
        values.add(value);
        keys.add(key);
    }

    public String sendRequest() throws JSONException {
        requestQueue = Volley.newRequestQueue(activity);

        showDialog();

        StringRequest request = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        setResponse_msg(response);
                        Log.d(TAG, response);
                        hideDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setResponse_msg("Network Unavailable");
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        hideDialog();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paramMap = new HashMap<>();
                for(int i=0; i<keys.size(); i++){
                    paramMap.put(keys.get(i), values.get(i));
                    Log.i(TAG,keys.get(i) + "   " + values.get(i));
                }
                return paramMap;
            }

        };

        request.setTag(requestTag);
        requestQueue.add(request);
        return getResponse();
    }


    public void setRequestTag(String tag){
        this.requestTag = tag;
    }

    public void setResponse_msg(String msg){
        response_msg = msg;
    }


    public String getResponse(){
        return response_msg;
    }

    private void showDialog() {
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    private void hideDialog() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    public void setProgessDialog(String pDialogMessage){
        mProgressDialog.setMessage(pDialogMessage);
    }

}









    //public void distroy(){
        //requestQueue.cancelAll(constants.TAG);
   // }


//    public String sendJSONRequest() throws JSONException {
//
//        requestQueue = Volley.newRequestQueue(activity);
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("data", "check");
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, SERVER_URL,
//                new JSONObject(map),
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        setResponse_msg(response);
//                        Log.d(TAG, response);
//                        Toast.makeText(activity, response, Toast.LENGTH_LONG).show();
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        setResponse_msg("Network is unreachable");
//                        Log.e(TAG, "Registration Error: " + error.getMessage());
//                        Toast.makeText(activity,
//                                error.getMessage(), Toast.LENGTH_LONG).show();
//                        //Log.i(constants.TAG,"ERROR OCCOURED DURING REQUEST :- "  + error.toString());
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> paramMap = new HashMap<>();
////                for(int i=0; i<params.size(); i++){
////                    paramMap.put(params.get(i), keys.get(i));
//                //paramMap.put("data", "{\"firstName\":\"A\",\"middleName\":\"A\",\"lastName\":\"A\",\"username\":\"check\",\"password\":\"a\",\"email\":\"check@check\",\"role\":\"ROLE_APP_USER\",\"phone\":\"0\"}");
//                paramMap.put("data", "check");
////                paramMap.put("email","bhanuka@gmail.com");
////                paramMap.put("password", "bhanuka");
//                return paramMap;
//            }
//
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String, String> headers = new HashMap<>();
////                headers.put("Content-Type", "application/json; charset=utf-8");
////                headers.put("User-agent", "My useragent");
////                return headers;
////
////            }
//        };
//
//        request.setTag(requestTag);
//
//        requestQueue.add(request);
//        return getResponse();
//
//    }

