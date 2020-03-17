package com.zhao.mawen.model;

import com.zhao.mawen.enums.LikedStatusEnum;
import com.zhao.mawen.enums.LikedTargetTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserLike {
    private Long id;

    //被点赞项目id
    private Long likedUserId;

    //点赞人
    private Long likedPostId;

    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    //点赞的项目类型
    private Integer type;
    private Date createTime;
    private Date updateTime;


    public UserLike(Long likedUserId, Long likedPostId, Integer type, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
        this.type = type;
    }
}
