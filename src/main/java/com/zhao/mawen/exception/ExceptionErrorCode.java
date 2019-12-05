package com.zhao.mawen.exception;

public enum ExceptionErrorCode implements IExceptionErrorCode{
    QUESTION_NOT_FOUND("问题不存在或已删除");

    @Override
    public String getMessage(){
        return message;
    }

    private String message;

    ExceptionErrorCode(String message) {
        this.message = message;
    }
}
