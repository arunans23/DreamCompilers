package com.example.arunan.dreamcompilers.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arunan.dreamcompilers.data.DiseaseBaseHelper;
import com.example.arunan.dreamcompilers.data.DiseaseCursorWrapper;
import com.example.arunan.dreamcompilers.data.DiseaseDbSchema.DiseaseTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by arunan on 12/10/16.
 */


//Singleton class to store disease log list

public class DiseaseLab {
    private static DiseaseLab sDiseaseLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DiseaseLab get(Context context){
        if (sDiseaseLab == null){
            sDiseaseLab = new DiseaseLab(context);
        }
        return sDiseaseLab;
    }

    private DiseaseLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new DiseaseBaseHelper(mContext)
                .getWritableDatabase();

    }

    public List<Disease> getDiseases(){
        List<Disease> diseases = new ArrayList<>();

        DiseaseCursorWrapper cursor = queryDiseases(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                diseases.add(cursor.getDisease());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return diseases;
    }

    public List<Disease> getUserDiseases(String username){

        List<Disease> userDiseases = new ArrayList<>();
        DiseaseCursorWrapper cursor = queryDiseases(
                DiseaseTable.Cols.USER_NAME + " = ?",
                new String[] {username}
        );

        try{

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                userDiseases.add(cursor.getDisease());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return userDiseases;
    }

    public void addDisease(Disease disease){
        ContentValues values = getContentValues(disease);

        mDatabase.insert(DiseaseTable.NAME, null, values);
    }

    public Disease getDisease(UUID id){
        DiseaseCursorWrapper cursor = queryDiseases(
                DiseaseTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try{
            if (cursor.getCount() == 0 ){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getDisease();
        } finally {
            cursor.close();
        }
    }


    public void updateDisease(Disease disease) {
        String uuidString = disease.getEntryId().toString();
        ContentValues values = getContentValues(disease);

        mDatabase.update(DiseaseTable.NAME, values, DiseaseTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    //use to contentvalues to insert and update database queries
    private static ContentValues getContentValues(Disease disease){
        ContentValues values = new ContentValues();
        values.put(DiseaseTable.Cols.UUID, disease.getEntryId().toString());
        values.put(DiseaseTable.Cols.TITLE, disease.getTitle());
        values.put(DiseaseTable.Cols.SYMPTOMS, disease.getSymptoms());
        values.put(DiseaseTable.Cols.DESCRIPTION, disease.getDescription());
        values.put(DiseaseTable.Cols.VICTIMCOUNT, disease.getNoVictims());
        values.put(DiseaseTable.Cols.SYNCED, disease.isSynced() ? 1 : 0 );
        values.put(DiseaseTable.Cols.USER_NAME, disease.getUserName());
        values.put(DiseaseTable.Cols.LOCATION, disease.getLocation());
        return values;
    }

    private DiseaseCursorWrapper queryDiseases(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DiseaseTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DiseaseCursorWrapper(cursor);
    }
}
