package com.zhao.mawen.exception;

public enum ExceptionErrorCode implements IExceptionErrorCode{
    NO_LOGIN(2000,"未登录"),
    QUESTION_NOT_FOUND(2001,"问题不存在或已删除"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题回复"),
    SYS_ERROR(2004,"error!!");

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    ExceptionErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
