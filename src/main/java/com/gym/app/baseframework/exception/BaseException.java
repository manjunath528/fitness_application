package com.gym.app.baseframework.exception;

import java.util.UUID;

/**
 * Created by Manjunath Reddy
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = 6280630325392423470L;
    private final String incidentID = UUID.randomUUID().toString();

    private int errorCode;
    private String errorDescription;
    private int httpStatus;

    public BaseException(String errorDescription) {
        super(errorDescription);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String errorDescription, int errorCode) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public BaseException(String errorDescription, int errorCode, Throwable cause) {
        super(errorDescription, cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public BaseException(String errorDescription, int errorCode, int httpStatus, Throwable cause) {
        super(errorDescription, cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.httpStatus = httpStatus;
    }

    public BaseException(int httpStatus, int errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getIncidentID() {
        return incidentID;
    }


}
