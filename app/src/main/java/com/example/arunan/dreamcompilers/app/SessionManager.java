package com.example.arunan.dreamcompilers.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by arunan on 12/13/16.
 */

public class SessionManager {

    private static String TAG = "sessionManager";

    SharedPreferences pref;
    Editor mEditor;
    Context mContext;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "DreamCompilersLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        mEditor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        mEditor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }


}
