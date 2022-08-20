package com.example.springmvchibernatecrm.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Collections;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> resourceNotFoundHandler(ResourceNotFoundException exception, HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto(
                404,
                exception.getMessage(),
                request.getMethod() + " " + request.getRequestURI(),
                Collections.emptyList()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionDto> badRequestHandler(BadRequestException exception, HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto(
                400,
                exception.getMessage(),
                request.getMethod() + " " + request.getRequestURI(),
                exception.getFieldErrorsMessages()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<ExceptionDto> sqlException(SQLException exception, HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto(
                500,
                exception.getMessage(),
                request.getMethod() + " " + request.getRequestURI(),
                Collections.emptyList()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionDto> methodNotAllowedError(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto(
                405,
                exception.getMessage(),
                request.getMethod() + " " + request.getRequestURI(),
                Collections.emptyList()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ExceptionDto> nullPointerException(NullPointerException exception) {
        ExceptionDto exceptionDto = new ExceptionDto(
                500,
                exception.getMessage(),
                "",
                Collections.emptyList()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ExceptionDto> noSuchElementExceptionHandler(NoSuchElementException exception, HttpServletRequest request){
        ExceptionDto exceptionDto = new ExceptionDto(
                400,
                exception.getMessage(),
                request.getMethod() + " " + request.getRequestURI(),
                Collections.emptyList()
        );
        logger.error(exceptionDto.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
