package com.example.arunan.dreamcompilers.models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by arunan on 12/13/16.
 */

public class UserInfo {
    private UUID mUserId;
    private String mFullName;
    private String mEmail;
    private String mPassword;
    private String Location;
    private String mRoleId;
    private Date mDate;
    private boolean mLogged;

    //constructor to call when entering new crime
    public UserInfo(){
        this(UUID.randomUUID());
        mDate = new Date();

    }

    //constructor to call when querying the database
    public UserInfo(UUID id){
        mUserId = id;
    }

    public UUID getUserId() {
        return mUserId;
    }

    public void setUserId(UUID userId) {
        mUserId = userId;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getRoleId() {
        return mRoleId;
    }

    public void setRoleId(String roleId) {
        mRoleId = roleId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isLogged() {
        return mLogged;
    }

    public void setLogged(boolean login) {
        mLogged = login;
    }
}
