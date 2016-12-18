package com.example.arunan.dreamcompilers.models;

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
    private String mUserEmail;
    private boolean isSynced;
    private String mLocation;

    //constructor to call when entering new crime
    public Disease(String userEmail){
        this(UUID.randomUUID());
        mUserEmail = userEmail;
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

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }
}
