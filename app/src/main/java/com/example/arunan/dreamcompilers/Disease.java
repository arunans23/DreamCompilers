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

    //constructor to call when entering new crime
    public Disease(){
        this(UUID.randomUUID());
        mDate = new Date();
        mTitle = "disease" + mEntryId.toString();

    }

    //constructor to call when querying the database
    public Disease(UUID id){
        mEntryId = id;

        mLastEditDate = new Date();
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

    public UUID getUserId() {
        return mUserId;
    }

    public void setUserId(UUID userId) {
        mUserId = userId;
    }
}
