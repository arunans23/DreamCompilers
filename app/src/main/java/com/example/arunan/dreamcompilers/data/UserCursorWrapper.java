package com.example.arunan.dreamcompilers.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.arunan.dreamcompilers.data.UserDbSchema.UserTable;
import com.example.arunan.dreamcompilers.models.UserInfo;

import java.util.UUID;

/**
 * Created by arunan on 12/13/16.
 */


//Cursor wrapper class to retrieve user info from db

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public UserInfo getUserInfo(){
        String uuidString = getString(getColumnIndex(UserTable.Cols.UUID));
        String fullName = getString(getColumnIndex(UserTable.Cols.FULLNAME));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String roleID = getString(getColumnIndex(UserTable.Cols.ROLE_ID));
        int login = getInt(getColumnIndex((UserTable.Cols.LOGIN)));

        UserInfo userInfo = new UserInfo(UUID.fromString(uuidString));
        userInfo.setFullName(fullName);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setRoleId(roleID);
        userInfo.setLogged(login!=0);


        return userInfo;
    }
}
