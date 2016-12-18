package com.example.arunan.dreamcompilers.models;

import java.util.UUID;

/**
 * Created by arunan on 12/13/16.
 */

public class UserInfo {
    private UUID mUserId;
    private String mFirstName;
    private String mMiddleName;
    private String mLastName;
    private String mEmail;
    private String mPassword;
    private String mRoleId;
    private String mPhoneNumber;
    private boolean mLogged;

    //constructor to call when entering new crime
    public UserInfo(){
        this(UUID.randomUUID());

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

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getMiddleName() {
        return mMiddleName;
    }

    public void setMiddleName(String middleName) {
        mMiddleName = middleName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
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

    public String getRoleId() {
        return mRoleId;
    }

    public void setRoleId(String roleId) {
        mRoleId = roleId;
    }

    public boolean isLogged() {
        return mLogged;
    }

    public void setLogged(boolean login) {
        mLogged = login;
    }
}
