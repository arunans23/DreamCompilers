package com.example.arunan.dreamcompilers.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.arunan.dreamcompilers.data.UserDbSchema.UserTable;
import com.example.arunan.dreamcompilers.models.UserInfo;

/**
 * Created by arunan on 12/13/16.
 */


//Cursor wrapper class to retrieve user info from db

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public UserInfo getUserInfo(){
        String userName = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String roleID = getString(getColumnIndex(UserTable.Cols.ROLE_ID));
        int login = getInt(getColumnIndex(UserTable.Cols.LOGIN));

//        String firstName = getString(getColumnIndex(UserDetailTable.Cols.FIRSTNAME));
//        String middleName = getString(getColumnIndex(UserDetailTable.Cols.MIDDLENAME));
//        String lastName = getString(getColumnIndex(UserDetailTable.Cols.LASTNAME));
//        String email = getString(getColumnIndex(UserDetailTable.Cols.EMAIL));
//        String phoneNumber = getString(getColumnIndex(UserDetailTable.Cols.PHONENUMBER));
//
//        String roleName = getString(getColumnIndex(UserRoleTable.Cols.ROLENAME));
//        int adminLevel = getInt(getColumnIndex(UserRoleTable.Cols.ADMINLEVEL));

        UserInfo userInfo = new UserInfo(userName);

        userInfo.setPassword(password);
        userInfo.setRoleId(roleID);
        userInfo.setLogged(login!=0);

//        userInfo.setFirstName(firstName);
//        userInfo.setMiddleName(middleName);
//        userInfo.setLastName(lastName);
//        userInfo.setEmail(email);
//        userInfo.setPhoneNumber(phoneNumber);
//
//        userInfo.setRoleName(roleName);
//        userInfo.setAdminLevel(adminLevel);

        return userInfo;
    }
}
