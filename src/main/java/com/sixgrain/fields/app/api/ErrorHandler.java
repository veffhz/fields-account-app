package com.sixgrain.fields.app.api;

import com.sixgrain.fields.app.exception.AccountNotFoundException;
import com.sixgrain.fields.app.exception.FieldNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorDetails> handle(AccountNotFoundException ex) {
        return new ResponseEntity<>(ErrorDetails.accountNotExist(ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<ErrorDetails> handle(FieldNotFoundException ex) {
        return new ResponseEntity<>(ErrorDetails.fieldNotExist(ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handle(Exception ex) {
        return new ResponseEntity<>(ErrorDetails.internalError(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
