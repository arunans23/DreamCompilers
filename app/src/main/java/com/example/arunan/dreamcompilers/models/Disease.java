package com.example.arunan.dreamcompilers.models;

import java.util.UUID;

/**
 * Created by arunan on 12/9/16.
 */

public class Disease {
    private String mEntryId;
    private String mTitle;
    private String mSymptoms;
    private String mDescription;
    private int mNoVictims;
    private String mUserName;
    private boolean isSynced;
    private String mLocation;

    //constructor to call when entering new crime
    public Disease(){
        this.mEntryId = UUID.randomUUID().toString();
        isSynced= false;

    }

    //constructor to call when querying the database
    public Disease(String entryId){
        mEntryId = entryId;
    }

    public String getEntryId() {
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


    public void setSymptoms(String symptoms) {
        mSymptoms = symptoms;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }


    public int getNoVictims() {
        return mNoVictims;
    }

    public void setNoVictims(int noVictims) {
        mNoVictims = noVictims;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }
}
