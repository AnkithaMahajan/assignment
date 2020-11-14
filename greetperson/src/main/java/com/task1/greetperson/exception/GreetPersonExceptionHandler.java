package com.task1.greetperson.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GreetPersonExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>("Invalid JSON passed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { GreetWordUnavailableException.class})
    protected ResponseEntity<Object> handleGreetWordUnavailableException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>("Greet Word could not be fetched. Please reach out to support or try after sometime. ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { PersonNameUnavailableException.class})
    protected ResponseEntity<Object> handlePersonNameUnavailableException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>("Person Name could not be fetched. Please reach out to support or try after sometime. ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { EmptyRequestException.class})
    protected ResponseEntity<Object> handleEmptyRequestException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>("Invalid request, first and last name of person is not given. ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
