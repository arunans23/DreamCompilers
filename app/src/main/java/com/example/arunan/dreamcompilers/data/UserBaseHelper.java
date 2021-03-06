package com.example.arunan.dreamcompilers.data;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.arunan.dreamcompilers.data.UserDbSchema.UserDetailTable;
import com.example.arunan.dreamcompilers.data.UserDbSchema.UserRoleTable;
import com.example.arunan.dreamcompilers.data.UserDbSchema.UserTable;

import java.util.ArrayList;

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
        db.execSQL("create table if not exists " + UserTable.NAME + "(" +
                UserTable.Cols.USERNAME + ", " +
                UserTable.Cols.PASSWORD + ", " +
                UserTable.Cols.ROLE_ID + ", " +
                UserTable.Cols.LOGIN + ", " +
                UserTable.Cols.TOKEN + ")"
        );

        db.execSQL("create table if not exists " + UserDetailTable.NAME + "(" +
                UserDetailTable.Cols.USERNAME + ", " +
                UserDetailTable.Cols.EMAIL + ", " +
                UserDetailTable.Cols.FIRSTNAME + ", " +
                UserDetailTable.Cols.MIDDLENAME + ", " +
                UserDetailTable.Cols.LASTNAME + ", " +
                UserDetailTable.Cols.PHONENUMBER +
                ")"

        );

        db.execSQL("create table if not exists " + UserRoleTable.NAME + "(" +
                UserRoleTable.Cols.ROLEID + ", " +
                UserRoleTable.Cols.ROLENAME + ", " +
                UserRoleTable.Cols.ADMINLEVEL + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserTable.NAME);

        db.execSQL("drop table if exists " + UserDetailTable.NAME);

        db.execSQL("drop table if exists " + UserRoleTable.NAME);

        onCreate(db);
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}
