package com.example.arunan.dreamcompilers.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.app.AppConfig;
import com.example.arunan.dreamcompilers.app.AppController;
import com.example.arunan.dreamcompilers.app.SessionManager;
import com.example.arunan.dreamcompilers.models.UserInfo;
import com.example.arunan.dreamcompilers.models.UserLab;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by arunan on 12/13/16.
 */

public class LoginActivity extends Activity{
    private static final String TAG = "LoginActivity";

    private Button mLoginButton;
    private Button mRegisterRedirect;
    private EditText mEmailText;
    private EditText mPasswordText;
    private ProgressDialog pDialog;
    private SessionManager mSessionManager;
    private UserLab mUserLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailText = (EditText)findViewById(R.id.login_username);
        mPasswordText = (EditText)findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.btnLogin);
        mRegisterRedirect = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        //progress dialog

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mUserLab = UserLab.get(this);

        mSessionManager = new SessionManager(getApplicationContext());

        if (mSessionManager.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, DiseaseListActivity.class);
            startActivity(intent);
            finish();

        }

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmailText.getText().toString().trim();
                String password = mPasswordText.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        mRegisterRedirect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void checkLogin(final String email, final String password){
        UserInfo mUserInfo = mUserLab.getUser(email);
        if (mUserInfo==null){
            Toast.makeText(getApplicationContext(),
                    "User does not exist",
                    Toast.LENGTH_SHORT)
                    .show();
            //checkOnlineLogin(email, password);
        }else{
            if (mUserInfo.getEmail().equals(email) && mUserInfo.getPassword().equals(password)){
                Intent intent = DiseaseListActivity.newIntent(this, email);
                startActivity(intent);
                finish();
            }else if(mUserInfo.getEmail().equals(email)) {
                Toast.makeText(getApplicationContext(),
                        "Email and Password do not match",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }

    private void checkOnlineLogin(final String email, final String password){
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.sURLlogin, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        mSessionManager.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String fullname = user.getString("fullname");
                        String email = user.getString("email");
                        String location = user.getString("location");
                        long date = user.getLong("date");
                        String roleID = user.getString("roleID");

                        UserInfo userInfo = new UserInfo(UUID.fromString(uid));
                        userInfo.setFullName(fullname);
                        userInfo.setEmail(email);
                        userInfo.setDate(new Date(date));
                        userInfo.setLocation(location);
                        userInfo.setRoleId(roleID);

                        // Inserting row in users table
                        mUserLab.addUser(userInfo);

                        // Launch main activity
                        /*
                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                        */
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
