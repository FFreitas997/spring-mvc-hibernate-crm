package com.example.springmvchibernatecrm.exceptions;

import java.util.HashMap;
import java.util.List;

public class ExceptionListDto extends StatusCodeMessage{

    private int statusCode;
    private String errorMessage;
    private String requestURI;
    private HashMap<String, List<String>> hashMap = new HashMap<>();

    public ExceptionListDto(int statusCode, String errorMessage, List<String> errors, String requestURI) {
        super();
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.requestURI = requestURI;
        hashMap.put("errors", errors);
    }

    public HashMap<String, List<String>> getHashMap() {
        return hashMap;
    }

    public int getStatusCode() {
        return statusCode;
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

}
