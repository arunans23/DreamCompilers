package com.example.arunan.dreamcompilers.models;

/**
 * Created by arunan on 12/13/16.
 */

public class UserInfo {

    private String mToken;
    private String mUsername;
    private String mPassword;
    private boolean mLogged;
    private String mRoleId;

    private String mFirstName;
    private String mMiddleName;
    private String mLastName;
    private String mEmail;
    private String mPhoneNumber;

    private String mRoleName;
    private int mAdminLevel;


    //constructor to call when entering new crime
    public UserInfo(String userName){
        mUsername = userName;

    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean isLogged() {
        return mLogged;
    }

    public void setLogged(boolean logged) {
        mLogged = logged;
    }

    public String getRoleId() {
        return mRoleId;
    }

    public void setRoleId(String roleId) {
        mRoleId = roleId;
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

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getRoleName() {
        return mRoleName;
    }

    public void setRoleName(String roleName) {
        mRoleName = roleName;
    }

    public int getAdminLevel() {
        return mAdminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        mAdminLevel = adminLevel;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }
}
