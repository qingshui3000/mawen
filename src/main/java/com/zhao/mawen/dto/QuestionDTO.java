package com.zhao.mawen.dto;

import com.zhao.mawen.model.Question;
import com.zhao.mawen.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间戳
     */
    private Long gmtCreate;

    /**
     * 修改时间戳
     */
    private Long gmtModified;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 标签
     */
    private String tag;

    /**
     * 问题描述
     */
    private String description;

    private User user;

}
