package com.zhao.mawen.enums;

import lombok.Getter;

@Getter
public enum LikedTargetTypeEnum {
    Question(1,"问题"),
    Comment(2,"评论");
    private Integer code;
    private String name;

    LikedTargetTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
