package com.zhao.mawen.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String account;
    private String password;
    private String token;
    private String userfaceUrl;
    private String bio;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
}
