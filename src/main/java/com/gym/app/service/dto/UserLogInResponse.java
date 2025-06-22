package com.gym.app.service.dto;


import java.io.Serializable;

public class UserLogInResponse implements Serializable {
    private String loginId;
    private String emailId;
    private String accountStatus;

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


    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "UserLogInResponse{" +
                "loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
