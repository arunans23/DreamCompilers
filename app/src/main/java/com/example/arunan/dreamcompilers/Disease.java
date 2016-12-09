package com.example.arunan.dreamcompilers;

import java.util.Date;
import java.util.UUID;

/**
 * Created by arunan on 12/9/16.
 */

public class Disease {
    private UUID mEntryId;
    private String mTitle;
    private String mSymptoms;
    private String mDescription;
    private int mNoVictims;
    private Date mDate;

    public Disease(){
        mEntryId = UUID.randomUUID();
        mTitle = "disease" + mEntryId.toString();
    }

    public UUID getEntryId() {
        return mEntryId;
    }

    public String getTitle() {
        return mTitle;
    }
}
