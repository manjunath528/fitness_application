package com.gym.app.service.dto;


import java.io.Serializable;

public class UserMembershipResponse implements Serializable {

    private String loginId;
    private String email;
    private String membershipName;


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }


    @Override
    public String toString() {
        return "UserMembershipResponse{" +
                ", email='" + email + '\'' +
                ", membershipName='" + membershipName + '\'' +
                '}';
    }
}
