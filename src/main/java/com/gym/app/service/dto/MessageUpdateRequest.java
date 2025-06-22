package com.gym.app.service.dto;

public class MessageUpdateRequest {
    private Long messageId;
    private String loginId;
    private String message;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }



    public String getMessage() {
        return message;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageUpdateRequest{" +
                "messageId=" + messageId +
                ", loginId=" + loginId +
                ", message='" + message + '\'' +
                '}';
    }
}
