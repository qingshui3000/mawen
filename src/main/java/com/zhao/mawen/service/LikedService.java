package com.zhao.mawen.service;

import com.zhao.mawen.dao.model.UserLike;

import java.util.List;

public interface LikedService {
    /**
     * 保存点赞记录
     * @param userLike
     * @return
     */
    void save(UserLike userLike);

    /**
     * 批量保存
     * @param list
     * @return
     */
    void saveAll(List<UserLike> list);

    /**
     * 通过点赞人和被点赞人的id查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    UserLike getByLikedUserIdAndLikedPostId(Long likedUserId,Long likedPostId,Integer type);

    /**
     * 将redis中的点赞记录刷新到MySQL
     */
    void transLikedFromRedis2DB();

    /**
     * 将redis中的点赞数量存入MySQL
     */
    void transLikedCountFromRedis2DB();

    boolean hasLiked(Long likedUserId,Long likedPostId,Integer type);


//    /**
//     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
//     * @param likedUserId 被点赞人的id
//     * @param pageable
//     * @return
//     */
//    Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable);
//
//    /**
//     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
//     * @param likedPostId
//     * @param pageable
//     * @return
//     */
//    Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable);


}
