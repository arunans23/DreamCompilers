package com.example.arunan.dreamcompilers.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.data.AndroidDatabaseManager;
import com.example.arunan.dreamcompilers.models.UserInfo;
import com.example.arunan.dreamcompilers.models.UserLab;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arunan on 12/13/16.
 */

public class LoginActivity extends Activity{
    private static final String TAG = "LoginActivity";

    private Button mLoginButton;
    private Button mRegisterRedirect;
    private EditText mUserNameText;
    private EditText mPasswordText;
    private UserLab mUserLab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mUserNameText = (EditText)findViewById(R.id.login_username);
        mPasswordText = (EditText)findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.btnLogin);
        mRegisterRedirect = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        mUserLab = UserLab.get(this);

        UserInfo userInfo = mUserLab.getLoggedUser();
        if (userInfo!=null){
            Intent intent = DiseaseListActivity.newIntent(this, userInfo.getUsername());
            startActivity(intent);
            finish();
        }

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = mUserNameText.getText().toString().trim();
                String password = mPasswordText.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(username, password);
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

        Button button = (Button)findViewById(R.id.databaseCheck);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(getApplicationContext(),AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
    }

    private void checkLogin(final String username, final String password){
        UserInfo mUserInfo = mUserLab.getUser(username);
        mUserInfo.setLogged(true);
        mUserLab.updateUser(mUserInfo);


    }

    private void checkOnlineLogin(final String username, final String password){
        String tag_string_req = "req_login";



    }

    private void processResponse(String response){
        try {
            JSONObject jsonRes = new JSONObject(response);
            boolean error = jsonRes.getBoolean("error");
            String token = jsonRes.getString("tokn");

            if (!error){
                JSONObject jsonUser = jsonRes.getJSONObject("user");
                String username = jsonRes.getString("username");
                String password = jsonRes.getString("password");
                String email = jsonRes.getString("email");
                String firstName = jsonRes.getString("firstName");
                String middleName = jsonRes.getString("middleName");
                String phone = jsonRes.getString("phone");

                UserInfo userInfo = new UserInfo(username);

            }else{
                Toast.makeText(this, token, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
