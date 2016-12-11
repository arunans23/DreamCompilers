package com.example.arunan.dreamcompilers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.arunan.dreamcompilers.DiseaseDbSchema.DiseaseTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by arunan on 12/11/16.
 */

//cursorwrapper class for eliminating code reuse
public class DiseaseCursorWrapper extends CursorWrapper{
    public DiseaseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Disease getDisease(){
        String uuidString = getString(getColumnIndex(DiseaseTable.Cols.UUID));
        String title = getString(getColumnIndex(DiseaseTable.Cols.TITLE));
        String symptoms = getString(getColumnIndex(DiseaseTable.Cols.SYMPTOMS));
        String description = getString(getColumnIndex(DiseaseTable.Cols.DESCRIPTION));
        int victimCount = getInt(getColumnIndex(DiseaseTable.Cols.VICTIMCOUNT));
        long date = getLong(getColumnIndex(DiseaseTable.Cols.DATE));
        long last_edit_date = getLong(getColumnIndex(DiseaseTable.Cols.LAST_EDIT_DATE));

        Disease disease = new Disease(UUID.fromString(uuidString));
        disease.setTitle(title);
        disease.setSymptoms(symptoms);
        disease.setDescription(description);
        disease.setNoVictims(victimCount);
        disease.setDate(new Date(date));
        disease.setLastEditDate(new Date(last_edit_date));

        return disease;
    }
}
