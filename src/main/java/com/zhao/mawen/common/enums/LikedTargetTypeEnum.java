package com.zhao.mawen.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LikedTargetTypeEnum {
    Question(1,"问题"),
    Comment(2,"评论");
    private Integer code;
    private String name;
}