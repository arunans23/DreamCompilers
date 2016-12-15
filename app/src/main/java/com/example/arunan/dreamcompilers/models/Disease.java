package com.example.arunan.dreamcompilers.models;

import java.util.Date;
import java.util.Random;
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
    private String mUserEmail;
    private boolean isSynced;

    //constructor to call when entering new crime
    public Disease(){
        this(UUID.randomUUID());
        mDate = new Date();
        mTitle = "disease" + new Random().nextInt(10000);
        mLastEditDate = new Date();
        isSynced= false;

    }

    //constructor to call when querying the database
    public Disease(UUID id){
        mEntryId = id;
    }

    public UUID getEntryId() {
        return mEntryId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public String getSymptoms() {
        return mSymptoms;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date){
        mDate = date;
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

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }
}
