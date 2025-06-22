package com.gym.app.baseframework.exception.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Manjunath Reddy
 */
public enum ApiErrors {

    BLOCKED_PROTOCOL(HttpStatus.UPGRADE_REQUIRED.value(), 1000, "Protocol Upgrade required"),
    JWT_TOKEN_INVALID_OR_EXPIRED(HttpStatus.UNAUTHORIZED.value(), 1001, "Provided JWT token is Invalid or Expired"),

    /*BusinessExceptions*/
    MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES(HttpStatus.BAD_REQUEST.value(), 4000, "Missing mandatory data"),
    INVALID_REQUEST(HttpStatus.NOT_ACCEPTABLE.value(), 4008, "Invalid request"),

    /*DataFormatExceptions*/
    INCORRECT_DATE_INTERVAL(HttpStatus.BAD_REQUEST.value(), 5000, "Start date cannot be greater than End date"),
    INVALID_DATE_PARAMETER(HttpStatus.BAD_REQUEST.value(), 5001, "Invalid date combination"),
    GENERIC_INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), 5002, "Invalid Request"),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST.value(), 5003, "Invalid date format"),
    INVALID_DATA(HttpStatus.BAD_REQUEST.value(), 5004, "Invalid data combination"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), 5405, "Method Not Allowed"),

    /*PersistenceExceptions*/
    ERROR_WHILE_SAVING(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6000, "Error while saving"),
    ERROR_WHILE_RETRIEVAL(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6001, "Error communicating with database"),
    PARTIALLY_UPDATED(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6002, "Partially updated"),
    ERROR_WHILE_DELETION(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6003, "Error while deletion"),
    NO_RECORD_FOUND(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6004, "No records found"),
    USER_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6005, "User account already exist"),
    USER_DOESNOT_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6006, "User account doesn't exist"),
    USER_HEALTH_STATUS_DOESNOT_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6012, "User health details doesn't exist"),
    USER_PERSONAL_STATUS_DOESNOT_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6013, "User personal details doesn't exist"),
    USER_ACCOUNT_INACTIVE(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6007, "User account is Inactive"),
    USER_ACCOUNT_PASSWORD_MISMATCH(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6008, "Login ID & password combination does not exist"),
    RECORD_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6011, "Record already exists"),
    INTERNAL_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 7004, "Internal processing error"),
    MESSAGE_ALREADY_READ(HttpStatus.INTERNAL_SERVER_ERROR.value(), 7005, "Message already read"),
    NOT_POSSIBLE_FOR_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), 7006, "Not possible for user");

    public static final Map<String, ApiErrors> LOOKUP_BY_ERROR_DESCRIPTION = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    public static final Map<Integer, ApiErrors> LOOKUP_BY_ERROR_CODE = new TreeMap<>();

    static {
        for (ApiErrors s : values()) {
            LOOKUP_BY_ERROR_DESCRIPTION.put(s.getErrorDescription(), s);
            LOOKUP_BY_ERROR_CODE.put(s.getErrorCode(), s);
        }
    }

    private final Integer httpStatus;
    private final Integer errorCode;
    private final String errorDescription;

    private ApiErrors(Integer httpStatus, Integer errorCode, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
