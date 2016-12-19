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

import com.android.volley.RequestQueue;
import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.app.AppConfig;
import com.example.arunan.dreamcompilers.app.AppController;
import com.example.arunan.dreamcompilers.models.UserInfo;
import com.example.arunan.dreamcompilers.models.UserLab;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.arunan.dreamcompilers.R.id.btnRegister;

/**
 * Created by arunan on 12/13/16.
 */

//register new activity
public class RegisterActivity extends Activity {
    private static final String TAG = "registerActivity";
    private Button mButtonRegister;
    private Button mRedirectLogin;
    private EditText mFirstNameText;
    private EditText mMiddleNameText;
    private EditText mLastNameText;
    private EditText mEmailText;
    private EditText mUserNameText;
    private EditText mPasswordText;
    private EditText mPhoneNumberText;
    private Spinner mRole;

    UserInfo mUserInfo;

    private ProgressDialog pDialog;
    private UserLab mUserLab;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstNameText = (EditText)findViewById(R.id.register_firstname);
        mMiddleNameText = (EditText)findViewById(R.id.register_middlename);
        mLastNameText = (EditText)findViewById(R.id.register_lastname);
        mEmailText = (EditText)findViewById(R.id.register_email);
        mUserNameText = (EditText)findViewById(R.id.register_username);
        mPasswordText = (EditText)findViewById(R.id.register_password);
        mPhoneNumberText = (EditText)findViewById(R.id.register_phone_number);
        mRole = (Spinner) findViewById(R.id.user_role_spinner);
        mButtonRegister = (Button) findViewById(btnRegister);
        mRedirectLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        mUserLab = UserLab.get(this);


        // Register Button Click event
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String firstname = mFirstNameText.getText().toString().trim();
                String middlename = mMiddleNameText.getText().toString().trim();
                String lastname = mLastNameText.getText().toString().trim();
                String email = mEmailText.getText().toString().trim();
                String username = mUserNameText.getText().toString().trim();
                String password = mPasswordText.getText().toString().trim();
                String phoneNumber = mPhoneNumberText.getText().toString().trim();
                String role = mRole.getSelectedItem().toString();

                String roleID;
                if (role.equals("Patient")){
                    roleID = AppConfig.ROLE_APP_USER;
                }else if (role.equals("Doctor")){
                    roleID = AppConfig.ROLE_DOCTOR;
                }else if(role.equals("Health Officer")){
                    roleID = AppConfig.ROLE_HEALTH_OFFICER;
                } else {
                    roleID = "ROLE_USER";
                }

                if (!firstname.isEmpty() && !lastname.isEmpty() && !username.isEmpty()
                        && !email.isEmpty() && !password.isEmpty() && !phoneNumber.isEmpty()) {
                    registerUser(firstname, middlename, lastname, email, username, password, phoneNumber, roleID);
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

    private void registerUser(final String firstname, final String middlename, final String lastname,
                                    final String email, final String username, final String password,
                                    final String phoneNumber, final String roleID) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        JSONObject json = new JSONObject();
        try {
            json.put("firstName", firstname);
            json.put("middleName", middlename);
            json.put("lastName", lastname);
            json.put("username", username);
            json.put("password", password);
            json.put("email", email);
            json.put("role", roleID);
            json.put("phone", phoneNumber);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "JSON error", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }

        final String jsonString = json.toString();
        Log.d(TAG, jsonString);

        final AppController request = new AppController(1,this);
        request.set_server_url(AppConfig.sURLregister);
        request.setParams("data", jsonString);

        try {
            String req = request.sendRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        hideDialog();


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
