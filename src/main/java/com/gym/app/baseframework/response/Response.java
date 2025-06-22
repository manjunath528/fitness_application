package com.gym.app.baseframework.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Created by Manjunath Reddy
 */
public class Response implements Serializable {

    private static final long serialVersionUID = -2426024380417141282L;

    private int errorCode;
    private String incidentID;
    private Object result;
    private String description;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(String incidentID) {
        this.incidentID = incidentID;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setError(int erroCode, String errorDescription) {
        this.errorCode = erroCode;
        this.description = errorDescription;
    }

    public String toJsonString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
