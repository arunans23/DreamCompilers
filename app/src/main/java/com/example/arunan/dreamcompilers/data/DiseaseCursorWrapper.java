package com.example.arunan.dreamcompilers.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.arunan.dreamcompilers.models.Disease;

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
        String uuidString = getString(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.UUID));
        String title = getString(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.TITLE));
        String symptoms = getString(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.SYMPTOMS));
        String description = getString(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.DESCRIPTION));
        int victimCount = getInt(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.VICTIMCOUNT));
        int isSynced = getInt(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.SYNCED));
        String userID = getString(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.USER_EMAIL));
        String location = getString(getColumnIndex(DiseaseDbSchema.DiseaseTable.Cols.LOCATION));

        Disease disease = new Disease(UUID.fromString(uuidString));
        disease.setTitle(title);
        disease.setSymptoms(symptoms);
        disease.setDescription(description);
        disease.setNoVictims(victimCount);
        disease.setSynced(isSynced != 0);
        disease.setUserEmail(userID);
        disease.setLocation(location);

        return disease;
    }
}
