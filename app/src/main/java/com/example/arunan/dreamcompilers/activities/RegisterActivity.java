package com.example.arunan.dreamcompilers.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
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

import static com.example.arunan.dreamcompilers.R.id.btnRegister;

/**
 * Created by arunan on 12/13/16.
 */

//register new activity
public class RegisterActivity extends Activity {
    private static final String TAG = "registerActivity";
    private Button mButtonRegister;
    private Button mRedirectLogin;
    private EditText mFullNameText;
    private EditText mEmailText;
    private EditText mPasswordText;
    private Spinner mDistrict;
    private Spinner mRole;

    private ProgressDialog pDialog;
    private SessionManager mSessionManager;
    private UserLab mUserLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullNameText = (EditText)findViewById(R.id.fullname);
        mEmailText = (EditText)findViewById(R.id.register_email);
        mPasswordText = (EditText)findViewById(R.id.register_password);
        mButtonRegister = (Button) findViewById(btnRegister);
        mRedirectLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        mDistrict = (Spinner) findViewById(R.id.user_district_spinner);
        mRole = (Spinner) findViewById(R.id.user_role_spinner);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mSessionManager = new SessionManager(getApplicationContext());

        mUserLab = UserLab.get(this);
        if (mSessionManager.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    DiseaseListActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = mFullNameText.getText().toString().trim();
                String email = mEmailText.getText().toString().trim();
                String password = mPasswordText.getText().toString().trim();
                String location = mDistrict.getSelectedItem().toString();
                String role = mDistrict.getSelectedItem().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    registerUser(name, email, password, location, role);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
        mRedirectLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void registerUser(final String name, final String email,
                              final String password, final String location, final String role){
        UserInfo userInfo = new UserInfo();
        userInfo.setFullName(name);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        //userInfo.setDate(new Date());
        userInfo.setRoleId(role);
        userInfo.setLocation(location);
        mUserLab.addUser(userInfo);

        Intent i = new Intent(getApplicationContext(),
                LoginActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerOnlineUser(final String name, final String email,
                              final String password, final String location, final String role) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.sURLregister, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String fullname = user.getString("fullname");
                        String email = user.getString("email");
                        long date = user.getLong("date");
                        String roleID = user.getString("roleID");
                        String location = user.getString("location");

                        UserInfo userInfo = new UserInfo(UUID.fromString(uid));
                        userInfo.setFullName(fullname);
                        userInfo.setEmail(email);
                        userInfo.setDate(new Date(date));
                        userInfo.setRoleId(roleID);
                        userInfo.setLocation(location);
                        // Inserting row in users table
                        mUserLab.addUser(userInfo);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                RegisterActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("fullname", name);
                params.put("email", email);
                params.put("password", password);
                params.put("location", location);

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
