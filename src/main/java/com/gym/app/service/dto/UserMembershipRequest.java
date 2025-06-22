package com.gym.app.service.dto;

public class UserMembershipRequest {
    private String loginId;
    private Long membershipId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public String toString() {
        return "UserMembershipRequest{" +
                "loginId='" + loginId + '\'' +
                ", membershipId=" + membershipId +
                '}';
    }
}
