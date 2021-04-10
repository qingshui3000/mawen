package com.zhao.mawen.common.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum LikedStatusEnum{
    ILKE(1,"点赞"),
    UNLIKE(0,"取消点赞/未点赞");
    private Integer code;
    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
