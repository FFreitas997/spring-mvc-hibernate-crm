package com.example.springmvchibernatecrm.exceptions;

import java.util.HashMap;
import java.util.List;

public class ExceptionDto extends StatusCodeMessage {
    private int statusCode;
    private String errorMessage;
    private String requestURI;

    private final HashMap<String, List<String>> hashMap = new HashMap<>();

    public ExceptionDto(int statusCode, String errorMessage, String requestURI, List<String> errorMessages) {
        super();
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.requestURI = requestURI;
        this.hashMap.put("errors", errorMessages);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HashMap<String, List<String>> getHashMap() {
        return hashMap;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return getMessageByStatus(statusCode);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    @Override
    public String toString() {
        return String.format("Exception ::  %d  %s  error message: %s  Request: %s", getStatusCode(), getStatusMessage(), getErrorMessage(), getRequestURI());
    }
}
