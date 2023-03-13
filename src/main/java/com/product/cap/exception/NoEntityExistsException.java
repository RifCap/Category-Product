package com.product.cap.exception;

public class NoEntityExistsException extends RuntimeException{

    private String Message;
    public NoEntityExistsException() {}

    public NoEntityExistsException(String message) {
        super(message);
        this.Message = message;
    }

}
