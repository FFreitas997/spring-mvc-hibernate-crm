package com.example.springmvchibernatecrm.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.List;

public class BadRequestException extends Exception {

    private final List<String> fieldErrorsMessages;

    public BadRequestException(String msg, BindingResult bindingResult) {
        super(msg);
        this.fieldErrorsMessages = bindingResult
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    public List<String> getFieldErrorsMessages() {
        return fieldErrorsMessages;
    }
}
