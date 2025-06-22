package com.gym.app.baseframework.response;

/**
 * Created by Manjunath Reddy
 */
public class HttpExceptionJson {
    private String incidentId;
    private int errorCode;
    private String errorDescription;

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return "HttpExceptionJson{" +
                "incidentId=" + incidentId +
                ", errorCode=" + errorCode +
                ", errorDescription=" + errorDescription +
                '}';
    }
}
