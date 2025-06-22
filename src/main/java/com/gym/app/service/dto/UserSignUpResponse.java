package com.gym.app.service.dto;


import java.io.Serializable;

public class UserSignUpResponse implements Serializable {
    private String loginId;
    private String emailId;
    private String personal_details_status;
    private String health_details_status;

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


    public String getPersonal_details_status() {
        return personal_details_status;
    }

    public void setPersonal_details_status(String personal_details_status) {
        this.personal_details_status = personal_details_status;
    }

    public String getHealth_details_status() {
        return health_details_status;
    }

    public void setHealth_details_status(String health_details_status) {
        this.health_details_status = health_details_status;
    }

    @Override
    public String toString() {
        return "UserSignUpResponse{" +
                "loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", personal_details_status='" + personal_details_status + '\'' +
                ", health_details_status='" + health_details_status + '\'' +
                '}';
    }
}