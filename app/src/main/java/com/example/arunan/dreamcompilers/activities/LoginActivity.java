package com.example.arunan.dreamcompilers.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arunan.dreamcompilers.R;
import com.example.arunan.dreamcompilers.app.AppConfig;
import com.example.arunan.dreamcompilers.app.AppController;
import com.example.arunan.dreamcompilers.data.AndroidDatabaseManager;
import com.example.arunan.dreamcompilers.models.Disease;
import com.example.arunan.dreamcompilers.models.DiseaseLab;
import com.example.arunan.dreamcompilers.models.UserInfo;
import com.example.arunan.dreamcompilers.models.UserLab;

import org.json.JSONArray;
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
    private DiseaseLab mDiseaseLab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserNameText = (EditText)findViewById(R.id.login_username);
        mPasswordText = (EditText)findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.btnLogin);
        mRegisterRedirect = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        mUserLab = UserLab.get(this);
        mDiseaseLab = DiseaseLab.get(this);

        UserInfo userInfo = mUserLab.getLoggedUser();
        if (userInfo!=null){
            Intent intent = DiseaseListActivity.newIntent(this, userInfo.getUsername());
            startActivity(intent);
            finish();
        }

        String responceCheck = "{\"error\":false,\"errorMsg\":\"login success\",\"tokn\":\"168908dd3227b8358eababa07fcaf091\",\"user\":{\"username\":\"shan\",\"password\":\"shan\",\"email\":\"shan\",\"firstName\":\"shan\",\"middleName\":\"shan\",\"phone\":\"shan\"},\"data\":[{\"diseaseDataId\":\"d01\",\"sysmptoms\":\"puka ridenawa\",\"description\":\"thiyanawa\",\"victimCount\":\"213\",\"locationCode\":\"81000\",\"entryId\":\"e01\"}]}";
        processResponse(responceCheck);

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

        JSONObject json = new JSONObject();
        try {
            json.put("username", username);
            json.put("password" , password);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "JSON error", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }

        final String jsonString = json.toString();
        Log.d(TAG, jsonString);

        final AppController request = new AppController(1,this);
        request.set_server_url(AppConfig.sURLregister);

        request.setParams("data", jsonString);
        request.setRequestTag(TAG);
        request.setProgessDialog("Logging in...");

        try {
            String req = request.sendRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CountDownTimer timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onFinish() {processResponse(request.getResponse()); }

            @Override
            public void onTick(long millisLeft) {
            }
        };
        timer.start();
    }


    private void processResponse(String response){
        if(response == ""){
            Toast.makeText(this,"Server Timeout", Toast.LENGTH_LONG).show();
        }else{
            try {
                JSONObject jsonRes = new JSONObject(response);
                boolean error = jsonRes.getBoolean("error");
                String errorMsg = jsonRes.getString("errorMsg");
                String token = jsonRes.getString("tokn");

                if (!error){
                    JSONObject jsonUser = jsonRes.getJSONObject("user");
                    String username = jsonUser.getString("username");
                    String password = jsonUser.getString("password");
                    String email = jsonUser.getString("email");
                    String firstName = jsonUser.getString("firstName");
                    String middleName = jsonUser.getString("middleName");
                    String phone = jsonUser.getString("phone");
                    //String role = jsonUser.getString("role");

                    UserInfo userInfo = new UserInfo(username);
                    userInfo.setPassword(password);
                    //userInfo.setRoleId(role);
                    userInfo.setToken(token);
                    userInfo.setLogged(true);

                    mUserLab.addUser(userInfo);

                    JSONArray diseaseArray = jsonRes.getJSONArray("data");
                    for (int m=0; m < diseaseArray.length(); m++){
                        JSONObject diseaseObject =diseaseArray.getJSONObject(m);
                        String diseaseTitle = diseaseObject.getString("diseaseDataId");
                        String diseaseSymptoms = diseaseObject.getString("sysmptoms");
                        String diseaseDescription = diseaseObject.getString("description");
                        int diseaseVictimCount = diseaseObject.getInt("victimCount");
                        String locationCode = diseaseObject.getString("locationCode");

                        Disease disease = new Disease(username);
                        disease.setSymptoms(diseaseSymptoms);
                        disease.setDescription(diseaseDescription);
                        disease.setLocation(locationCode);
                        disease.setNoVictims(diseaseVictimCount);

                        mDiseaseLab.addDisease(disease);
                    }

                    Intent i = DiseaseListActivity.newIntent(this, username);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(this, token, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(this, "JSON PARSE ERROR", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

    }
}
