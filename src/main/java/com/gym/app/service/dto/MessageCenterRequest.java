package com.gym.app.service.dto;

import java.io.Serializable;

public class MessageCenterRequest implements Serializable {

    private String targetLoginId;
    private String sourceLoginId;
    private String message;

    public String getTargetLoginId() {
        return targetLoginId;
    }

    public void setTargetLoginId(String targetLoginId) {
        this.targetLoginId = targetLoginId;
    }

    public String getSourceLoginId() {
        return sourceLoginId;
    }

    public void setSourceLoginId(String sourceLoginId) {
        this.sourceLoginId = sourceLoginId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "MessageCenterRequest{" +
                "targetLoginId='" + targetLoginId + '\'' +
                ", sourceLoginId='" + sourceLoginId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
