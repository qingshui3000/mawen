package com.zhao.mawen.model;

import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment {
    private Long id;

    private Long parentId;

    private Integer type;

    private Integer commentor;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private Integer commentCount;

    private String content;
}