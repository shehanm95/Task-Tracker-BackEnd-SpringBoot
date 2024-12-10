package com.esaternperarl.tasktracker.controller;

import com.esaternperarl.tasktracker.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    ResponseEntity<ErrorResponse> handleSqlException(
            RuntimeException ex, WebRequest request
    ){
       ErrorResponse res = new ErrorResponse(
               HttpStatus.BAD_REQUEST,
               ex.getMessage(),
               request.getDescription(false)
       );

       return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            RuntimeException ex, WebRequest request
    ){
       ErrorResponse res = new ErrorResponse(
               HttpStatus.BAD_REQUEST,
               ex.getMessage(),
               request.getDescription(false)
       );

       return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalStateException.class})
    ResponseEntity<ErrorResponse> handleIllegalStateException(
            RuntimeException ex, WebRequest request
    ){
       ErrorResponse res = new ErrorResponse(
               HttpStatus.BAD_REQUEST,
               ex.getMessage(),
               request.getDescription(false)
       );

       return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }


}
