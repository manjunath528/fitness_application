package com.gym.app.service.dto;
import java.io.Serializable;

public class ForgotAccountResponse implements Serializable {
    private String emailId;
    private String loginId;
    private String status;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ForgotAccountResponse{" +
                "emailId='" + emailId + '\'' +
                ", loginId='" + loginId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}