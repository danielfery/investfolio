package com.example.investfolio.entity;

public class ProfileDataOfUser {
    private String userId;
    private String eMail;

    public ProfileDataOfUser(String userId, String eMail) {
        this.userId = userId;
        this.eMail = eMail;
    }

    public ProfileDataOfUser() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
