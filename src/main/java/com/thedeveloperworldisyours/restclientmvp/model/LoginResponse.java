package com.thedeveloperworldisyours.restclientmvp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by javiergonzalezcabezas on 9/11/15.
 */
public class LoginResponse  implements Parcelable {
    private  String mCreatedAt;
    private  String mObjectId;
    private  String mSessionToken;
    private  String mUpdatedAt;
    private  String mUsername;
    private int mCode;
    private String info;
    private boolean mInterneterror;
    public LoginResponse() {}
    protected LoginResponse(Parcel in) {
        mCreatedAt = in.readString();
        mObjectId = in.readString();
        mSessionToken = in.readString();
        mUpdatedAt = in.readString();
        mUsername = in.readString();
        mCode = in.readInt();
        mInterneterror = in.readByte() != 0;
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCreatedAt);
        dest.writeString(mObjectId);
        dest.writeString(mSessionToken);
        dest.writeString(mUpdatedAt);
        dest.writeString(mUsername);
    }
}
