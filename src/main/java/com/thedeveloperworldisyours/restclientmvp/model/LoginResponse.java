package com.thedeveloperworldisyours.restclientmvp.model;

import java.io.Serializable;

/**
 * Created by javiergonzalezcabezas on 9/11/15.
 */
public class LoginResponse  implements Serializable {
    private  String mCreatedAt;
    private  String mObjectId;
    private  String mSessionToken;
    private  String mUpdatedAt;
    private  String mUsername;
    private int mCode;
    private boolean mInterneterror;

    public boolean checkStatusCode(int status) {

        boolean successful = false;

        if (status >=200 && status<300){
            successful = true;
        }
        return successful;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public boolean isInterneterror() {
        return mInterneterror;
    }

    public void setInterneterror(boolean interneterror) {
        this.mInterneterror = interneterror;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.mCreatedAt = createdAt;
    }

    public String getObjectId() {
        return mObjectId;
    }

    public void setObjectId(String objectId) {
        this.mObjectId = objectId;
    }

    public String getSessionToken() {
        return mSessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.mSessionToken = sessionToken;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.mUpdatedAt = updatedAt;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "createdAt='" + mCreatedAt + '\'' +
                ", objectId='" + mObjectId + '\'' +
                ", sessionToken='" + mSessionToken + '\'' +
                ", updatedAt='" + mUpdatedAt + '\'' +
                ", username='" + mUsername + '\'' +
                '}';
    }
}
