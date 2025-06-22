package com.gym.app.service.dto;

import com.gym.app.baseframework.exception.enums.ApiErrors;

public class ApiErrorResponse {
    private int status;
    private int errorCode;
    private String message;
    private long timestamp;
    private String path;

    public ApiErrorResponse(ApiErrors error, String path) {
        this.status = error.getHttpStatus();
        this.errorCode = error.getErrorCode();
        this.message = error.getErrorDescription();
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
