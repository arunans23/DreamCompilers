package com.example.arunan.dreamcompilers.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL("create table " + DiseaseDbSchema.DiseaseTable.NAME + "(" +
                DiseaseDbSchema.DiseaseTable.Cols.UUID + " primary key, " +
                DiseaseDbSchema.DiseaseTable.Cols.TITLE + ", " +
                DiseaseDbSchema.DiseaseTable.Cols.SYMPTOMS + ", " +
                DiseaseDbSchema.DiseaseTable.Cols.DESCRIPTION + ", " +
                DiseaseDbSchema.DiseaseTable.Cols.VICTIMCOUNT + ", " +
                DiseaseDbSchema.DiseaseTable.Cols.DATE + ", " +
                DiseaseDbSchema.DiseaseTable.Cols.LAST_EDIT_DATE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
