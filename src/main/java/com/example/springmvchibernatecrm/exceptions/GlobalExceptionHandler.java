package com.example.springmvchibernatecrm.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> resourceNotFoundHandler(ResourceNotFoundException exception, HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto(
                404,
                exception.getMessage(),
                request.getMethod() + " " + request.getRequestURI()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionListDto> badRequestHandler(BadRequestException exception, HttpServletRequest request) {
        ExceptionListDto exceptionDto = new ExceptionListDto(
                400,
                exception.getMessage(),
                exception.getFieldErrorsMessages(),
                request.getMethod() + " " + request.getRequestURI()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
