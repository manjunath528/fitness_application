package com.gym.app.service.dto;

import java.io.Serializable;

public class UserHealthResponse implements Serializable {
    private String loginId;
    private String health_details_status;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getHealth_details_status() {
        return health_details_status;
    }

    public void setHealth_details_status(String health_details_status) {
        this.health_details_status = health_details_status;
    }

    @Override
    public String toString() {
        return "UserHealthProfileResponse{" +
                "loginId='" + loginId + '\'' +
                ", health_details_status='" + health_details_status + '\'' +
                '}';
    }
}
