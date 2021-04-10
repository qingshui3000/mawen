package com.zhao.mawen.controller.dto;

import com.zhao.mawen.common.enums.IExceptionErrorCode;

public class MawenException extends RuntimeException{
    private String message;
    private Integer code;

    public MawenException(IExceptionErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
