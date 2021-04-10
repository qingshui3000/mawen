package com.zhao.mawen.dao.model;

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

    private Long commentor;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private Integer commentCount;

    private String content;
}