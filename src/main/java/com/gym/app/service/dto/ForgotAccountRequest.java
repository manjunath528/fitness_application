package com.gym.app.service.dto;

import java.io.Serializable;

public class ForgotAccountRequest implements Serializable {
    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "ForgotAccountRequest{" +
                "emailId='" + emailId + '\'' +
                '}';
    }
}
