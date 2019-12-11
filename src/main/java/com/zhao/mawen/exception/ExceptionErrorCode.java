package com.zhao.mawen.exception;

public enum ExceptionErrorCode implements IExceptionErrorCode{
    NO_LOGIN(2000,"未登录，请登陆后重试"),
    QUESTION_NOT_FOUND(2001,"问题不存在或已删除"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题回复"),
    SYS_ERROR(2004,"error!!"),
    TYPE_PARAM_NOT_FOUND(2005,"服务器炸了"),
    COMMENT_NOT_FOUND(2006,"回复的评论消失了"),
    CONTENT_IS_EMPTY(2007,"回复内容不能为空！");

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
