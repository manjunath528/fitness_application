package com.gym.app.service.dto;


import java.io.Serializable;

public class MessageCenterUpdateRequest implements Serializable {
    private Long messageCenterId;
    private String loginId;
    private String isRead;
    private String status;
    private String messageAcceptanceStatus;

    public Long getMessageCenterId() {
        return messageCenterId;
    }

    public void setMessageCenterId(Long messageCenterId) {
        this.messageCenterId = messageCenterId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageAcceptanceStatus() {
        return messageAcceptanceStatus;
    }

    public void setMessageAcceptanceStatus(String messageAcceptanceStatus) {
        this.messageAcceptanceStatus = messageAcceptanceStatus;
    }

    @Override
    public String toString() {
        return "MessageCenterUpdateRequest{" +
                "messageCenterId=" + messageCenterId +
                ", loginId='" + loginId + '\'' +
                ", isRead='" + isRead + '\'' +
                ", status='" + status + '\'' +
                ", messageAcceptanceStatus='" + messageAcceptanceStatus + '\'' +
                '}';
    }
}
