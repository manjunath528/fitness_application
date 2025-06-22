package com.gym.app.service.dto;

import java.io.Serializable;

public class RetrieveUserProfileRequest implements Serializable {
    private String loginId = "";
    private String emailId = "";

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "RetrieveUserProfileRequest{" +
                "loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}