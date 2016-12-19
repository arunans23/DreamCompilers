package com.example.arunan.dreamcompilers.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.arunan.dreamcompilers.data.DiseaseDbSchema.DiseaseTable;

/**
 * Created by arunan on 12/11/16.
 */

//SQLite helper class to manage the database

public class DiseaseBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "diseaseBase.db";

    public DiseaseBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DiseaseTable.NAME + "(" +
                DiseaseTable.Cols.UUID + " primary key, " +
                DiseaseTable.Cols.TITLE + ", " +
                DiseaseTable.Cols.SYMPTOMS + ", " +
                DiseaseTable.Cols.DESCRIPTION + ", " +
                DiseaseTable.Cols.VICTIMCOUNT + ", " +
                DiseaseTable.Cols.SYNCED + ", " +
                DiseaseTable.Cols.USER_NAME + ", " +
                DiseaseTable.Cols.LOCATION + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
