package com.gym.app.service.dto;

import java.io.Serializable;

public class UserLogOutResponse implements Serializable {
    private String loginId;
    private String message;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserLogOutResponse{" +
                "loginId='" + loginId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}