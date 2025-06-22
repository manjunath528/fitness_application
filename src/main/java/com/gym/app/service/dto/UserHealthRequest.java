package com.gym.app.service.dto;

public class UserHealthRequest {
    private String loginId;
    private HealthInformation healthInformation;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public void setHealthInformation(HealthInformation healthInformation) {
        this.healthInformation = healthInformation;
    }

    @Override
    public String toString() {
        return "UserHealthProfileRequest{" +
                "loginId='" + loginId + '\'' +
                ", healthInformation=" + healthInformation +
                '}';
    }
}
