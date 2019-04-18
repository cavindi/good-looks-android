package com.ludowica.goodlooks.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userId")
    private int userId;

    @SerializedName("fName")
    private String fName;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
