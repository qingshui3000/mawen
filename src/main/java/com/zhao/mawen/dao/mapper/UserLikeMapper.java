package com.zhao.mawen.dao.mapper;

import com.zhao.mawen.dao.model.UserLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLikeMapper {

    void insert(UserLike userLike);

    void insertAll(List<UserLike> list);

    UserLike find(@Param("likedUserId") Long likedUserId, @Param("likedPostId") Long likedPostId,@Param("type") Integer type);

    void update(UserLike ul);
}
