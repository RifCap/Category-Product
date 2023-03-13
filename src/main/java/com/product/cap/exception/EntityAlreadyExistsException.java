package com.product.cap.exception;

public class EntityAlreadyExistsException extends RuntimeException{
    private String Message;
    public EntityAlreadyExistsException(){}
    public EntityAlreadyExistsException(String message) {
        super(message);
        this.Message = message;
    }
}
