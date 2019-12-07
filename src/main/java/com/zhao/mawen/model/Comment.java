package com.zhao.mawen.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * comment
 * @author 
 */
@Data
public class Comment{
    private Long id;

    private Integer parentId;

    private Integer type;

    private Integer commentor;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private String content;
}