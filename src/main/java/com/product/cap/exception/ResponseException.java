package com.product.cap.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseException {
    private String message;
    private HttpStatus httpStatus;
    private int status ;
    private ZonedDateTime timestamp;

}
