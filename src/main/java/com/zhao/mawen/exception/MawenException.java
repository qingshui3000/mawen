package com.zhao.mawen.exception;

public class MawenException extends RuntimeException{
    private String message;

    public MawenException(IExceptionErrorCode code) {
        this.message = code.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
