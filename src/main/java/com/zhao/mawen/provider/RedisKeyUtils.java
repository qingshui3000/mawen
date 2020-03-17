package com.zhao.mawen.provider;

public class RedisKeyUtils {
    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //点赞数量的key
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";
//    //点赞目标的类型
//    public static final Integer LIKED_COMMENT = 1;
//    public static final Integer LIKED_QUESTION = 2;
    /**
     * 拼接生产key
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    public static String getLikedKey(String likedUserId,String likedPostId,Integer type){
        StringBuilder sb = new StringBuilder();
        sb.append(likedUserId);
        sb.append("::");
        sb.append(likedPostId);
        sb.append("::");
        sb.append(type);
        return sb.toString();
    }

    public static String getLikedKey(String likedUserId,Integer type){
        StringBuilder sb = new StringBuilder();
        sb.append(likedUserId);
        sb.append("::");
        sb.append(type);
        return sb.toString();
    }
}
