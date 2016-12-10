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
    private Date mLastEditDate;
    private UUID mUserId;


    public Disease(){
        mEntryId = UUID.randomUUID();
        mTitle = "disease" + mEntryId.toString();
        mDate = new Date();
    }

    public UUID getEntryId() {
        return mEntryId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSymptoms() {
        return mSymptoms;
    }

    public Date getDate() {
        return mDate;
    }

    public void setSymptoms(String symptoms) {
        mSymptoms = symptoms;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getLastEditDate() {
        return mLastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        mLastEditDate = lastEditDate;
    }

    public int getNoVictims() {
        return mNoVictims;
    }

    public void setNoVictims(int noVictims) {
        mNoVictims = noVictims;
    }

    public UUID getUserId() {
        return mUserId;
    }

    public void setUserId(UUID userId) {
        mUserId = userId;
    }
}
