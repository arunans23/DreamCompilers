package com.example.arunan.dreamcompilers.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.arunan.dreamcompilers.data.UserDbSchema.UserTable;

/**
 * Created by arunan on 12/13/16.
 */

public class UserBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.NAME + "(" +
                UserTable.Cols.UUID + " primary key, " +
                UserTable.Cols.FULLNAME + ", " +
                UserTable.Cols.EMAIL + ", " +
                UserTable.Cols.PASSWORD  + ", " +
                UserTable.Cols.LOCATION+ ", " +
                UserTable.Cols.DATE + ", " +
                UserTable.Cols.ROLE_ID + ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
