package com.gym.app.service.dto;

import com.gym.app.entity.UserHealthDetails;

public class UserHealthProfileResponse {
    private String loginId;
    private String emailId;
    private String accountStatus;
    private UserHealthDetails userHealthDetails;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public UserHealthDetails getUserHealthDetails() {
        return userHealthDetails;
    }

    public void setUserHealthDetails(UserHealthDetails userHealthDetails) {
        this.userHealthDetails = userHealthDetails;
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
        return "UserHealthProfileResponse{" +
                "loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", healthInformation=" + userHealthDetails +
                '}';
    }
}
