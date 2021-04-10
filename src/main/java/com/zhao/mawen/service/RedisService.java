package com.zhao.mawen.service;

import com.zhao.mawen.controller.dto.LikedCountDTO;
import com.zhao.mawen.dao.model.UserLike;

import java.util.List;

public interface RedisService {
    /**
     * 点赞，状态设为1
     * @param likedUserId
     * @param likedPostId
     */
    void saveLiked2Redis(String likedUserId,String likedPostId,Integer type);

    /**
     * 取消点赞，状态设为0
     * @param likedUserId
     * @param likedPostId
     */
    void unlikedFromRedis(String likedUserId,String likedPostId,Integer type);

    /**
     * 从Redis中删除一条点赞记录
     * @param likedUserId
     * @param likedPostId
     */
    void deleteLikedFromRedis(String likedUserId,String likedPostId,Integer type);

    /**
     * 该用户的点赞数加1
     * @param likedUserId
     */
    void incrementLikedCount(String likedUserId,Integer type);

    /**
     * 该用户的点赞数减1
     * @param likedUserId
     */
    void decrementLikedCount(String likedUserId,Integer type);

    /**
     * 获取redis中缓存的所有点赞数据
     * @return
     */
    List<UserLike> getLikedDataFromRedis();

    /**
     * 获取Redis中缓存的所有点赞的数量
     * @return
     */
    List<LikedCountDTO> getLikedCountFromRedis();

//    LikedCountDTO getLikedCount();

}
