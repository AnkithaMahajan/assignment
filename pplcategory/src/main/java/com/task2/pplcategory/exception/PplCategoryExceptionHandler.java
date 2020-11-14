package com.task2.pplcategory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PplCategoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PplCategoryNotFoundException.class})
    protected ResponseEntity<Object> exception(PplCategoryNotFoundException exception) {
        return new ResponseEntity<>("Person Category not found", HttpStatus.NOT_FOUND);
    }

}
