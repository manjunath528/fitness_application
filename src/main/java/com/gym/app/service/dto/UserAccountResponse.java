package com.gym.app.service.dto;


import java.io.Serializable;

public class UserAccountResponse implements Serializable {
    private String loginId;
    private String personal_details_status;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPersonal_details_status() {
        return personal_details_status;
    }


    public void setPersonal_details_status(String personoal_details_status) {
        this.personal_details_status = personoal_details_status;

    }

    @Override
    public String toString() {
        return "UserAccountResponse{" +
                "loginId='" + loginId + '\'' +
                ", personal_details_status='" + personal_details_status + '\'' +
                '}';
    }
}
