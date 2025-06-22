package com.gym.app.service.dto;


import java.io.Serializable;

public class UserSignUpRequest implements Serializable {
    private String loginId;
    private String emailId;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "UserSignUpRequest{" +
                "loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
