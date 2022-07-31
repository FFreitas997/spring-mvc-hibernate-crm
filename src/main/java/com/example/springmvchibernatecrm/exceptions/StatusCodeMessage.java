package com.example.springmvchibernatecrm.exceptions;

import java.util.HashMap;

public class StatusCodeMessage {

    private final HashMap<Integer, String> statusCodeMap = new HashMap<>();

    public StatusCodeMessage() {
        statusCodeMap.put(200, "OK");
        statusCodeMap.put(302, "Found");
        statusCodeMap.put(303, "See Other");
        statusCodeMap.put(400, "Bad Request");
        statusCodeMap.put(401, "Unauthorized");
        statusCodeMap.put(403, "Forbidden");
        statusCodeMap.put(404, "Not Found");
        statusCodeMap.put(500, "Internal Server Error");
    }

    public String getMessageByStatus(Integer statusCode) {
        return statusCodeMap.containsKey(statusCode) ? statusCodeMap.get(statusCode) : "Status code " + statusCode + "dont exists in Status Code Map";
    }
}
