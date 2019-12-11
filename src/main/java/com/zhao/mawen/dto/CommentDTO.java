package com.zhao.mawen.dto;

import com.zhao.mawen.model.User;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private Integer commentor;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;
    
    private Integer commentCount;

    private String content;

    private User user;
}