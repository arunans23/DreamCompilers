package com.example.arunan.dreamcompilers.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arunan.dreamcompilers.data.UserBaseHelper;
import com.example.arunan.dreamcompilers.data.UserCursorWrapper;
import com.example.arunan.dreamcompilers.data.UserDbSchema.UserDetailTable;
import com.example.arunan.dreamcompilers.data.UserDbSchema.UserRoleTable;
import com.example.arunan.dreamcompilers.data.UserDbSchema.UserTable;

/**
 * Created by arunan on 12/13/16.
 */

//class to manage user details
public class UserLab {
    //Singleton class to store user log list

    private static UserLab sUserLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserLab get(Context context){
        if (sUserLab == null){
            sUserLab = new UserLab(context);
        }
        return sUserLab;
    }

    private UserLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new UserBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addUser(UserInfo userInfo){
        ContentValues values = getUserContentValues(userInfo);
        mDatabase.insert(UserTable.NAME, null, values);
    }

    public UserInfo getUser(String username){
        UserCursorWrapper cursor = queryUsers(
                UserTable.Cols.USERNAME + " = ?",
                new String[] {username}
        );

        try{
            if (cursor.getCount() == 0 ){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUserInfo();
        } finally {
            cursor.close();
        }
    }

    public UserInfo getLoggedUser(){
        UserCursorWrapper cursor = queryUsers(
                UserTable.Cols.LOGIN + " = 1",
                null
        );

        try{
            if (cursor.getCount() == 0 ){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getUserInfo();
        } finally {
            cursor.close();
        }
    }


    //use to contentvalues to insert and update database queries

    private static ContentValues getUserContentValues(UserInfo userInfo){
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERNAME, userInfo.getUsername());
        values.put(UserTable.Cols.PASSWORD, userInfo.getPassword());
        values.put(UserTable.Cols.ROLE_ID, userInfo.getRoleId());
        values.put(UserTable.Cols.LOGIN, userInfo.isLogged() ? 1 : 0);
        return values;
    }

    private static ContentValues getUserDetailContentValues(UserInfo userInfo){
        ContentValues values = new ContentValues();
        values.put(UserDetailTable.Cols.FIRSTNAME, userInfo.getFirstName());
        values.put(UserDetailTable.Cols.MIDDLENAME, userInfo.getMiddleName());
        values.put(UserDetailTable.Cols.LASTNAME, userInfo.getLastName());
        values.put(UserDetailTable.Cols.EMAIL, userInfo.getEmail());
        values.put(UserDetailTable.Cols.PHONENUMBER, userInfo.getPhoneNumber());
        return values;
    }

    private static ContentValues getUserRoleContentValues(UserInfo userInfo){
        ContentValues values = new ContentValues();
        values.put(UserRoleTable.Cols.ROLENAME, userInfo.getRoleName());
        values.put(UserRoleTable.Cols.ADMINLEVEL, userInfo.getAdminLevel());
        return values;
    }

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                UserTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new UserCursorWrapper(cursor);
    }



    public void updateUser(UserInfo userInfo) {
        String username = userInfo.getUsername();
        ContentValues values = getUserContentValues(userInfo);

        mDatabase.update(UserTable.NAME, values, UserTable.Cols.USERNAME + " = ?",
                new String[] {username});
    }
}
