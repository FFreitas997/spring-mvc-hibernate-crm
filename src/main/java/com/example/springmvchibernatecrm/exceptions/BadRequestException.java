package com.example.springmvchibernatecrm.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class BadRequestException extends Exception {

    private final List<String> fieldErrorsMessages;

    public BadRequestException(String msg, List<FieldError> errors) {
        super(msg);
        this.fieldErrorsMessages = errors
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    public List<String> getFieldErrorsMessages() {
        return fieldErrorsMessages;
    }
}
