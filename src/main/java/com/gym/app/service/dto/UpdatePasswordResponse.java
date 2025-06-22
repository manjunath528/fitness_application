package com.gym.app.service.dto;

import java.io.Serializable;

public class UpdatePasswordResponse implements Serializable {
    private String loginId;
    private String emailId;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdatePasswordResponse{" +
                "loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}