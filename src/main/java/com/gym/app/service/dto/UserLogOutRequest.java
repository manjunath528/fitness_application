package com.gym.app.service.dto;


import java.io.Serializable;

public class UserLogOutRequest implements Serializable {
    private String loginId;
    private String token;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserSignUpRequest{" +
                "loginId='" + loginId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}