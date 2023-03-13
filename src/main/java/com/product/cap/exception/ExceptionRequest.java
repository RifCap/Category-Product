package com.product.cap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionRequest {

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public ResponseEntity<Object> HandleApiEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ResponseException apiException = new ResponseException(
                e.getMessage(),
                badRequest,
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {NoEntityExistsException.class})
    public ResponseEntity<Object> HandleApiNoEntityExistsException(NoEntityExistsException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ResponseException apiException = new ResponseException(
                e.getMessage(),
                badRequest,
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
