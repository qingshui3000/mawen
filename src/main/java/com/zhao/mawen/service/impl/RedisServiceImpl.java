package com.zhao.mawen.service.impl;

import com.zhao.mawen.controller.dto.LikedCountDTO;
import com.zhao.mawen.common.enums.LikedStatusEnum;
import com.zhao.mawen.dao.model.UserLike;
import com.zhao.mawen.common.provider.RedisKeyUtils;
import com.zhao.mawen.service.LikedService;
import com.zhao.mawen.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    LikedService likedService;

    /**
     * 点赞，状态设为1
     * @param likedUserId
     * @param likedPostId
     */
    @Override
    public void saveLiked2Redis(String likedUserId, String likedPostId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedUserId,likedPostId,type);
        Object o = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        if(o == null){
            redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key, LikedStatusEnum.ILKE.getCode().toString());
        }

    }

    /**
     * 取消点赞，状态设为0
     * @param likedUserId
     * @param likedPostId
     */
    @Override
    public void unlikedFromRedis(String likedUserId, String likedPostId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedUserId,likedPostId,type);
        Object o = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        if(o == null){
            redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key,LikedStatusEnum.UNLIKE.getCode().toString());
        }
    }

    /**
     * 从Redis中删除一条点赞记录
     * @param likedUserId
     * @param likedPostId
     */
    @Override
    public void deleteLikedFromRedis(String likedUserId, String likedPostId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedUserId,likedPostId,type);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED,key);
    }

    /**
     * 该用户的点赞数加1
     * @param likedUserId
     */
    @Override
    public void incrementLikedCount(String likedUserId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedUserId,type);
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,key,1);
    }

    /**
     * 该用户的点赞数减1
     * @param likedUserId
     */
    @Override
    public void decrementLikedCount(String likedUserId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedUserId,type);
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,key,-1);
    }


    /**
     * 获取redis中缓存的所有点赞数据
     * @return
     */
    @Override
    public List<UserLike> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while(cursor.hasNext()){
            Map.Entry<Object,Object> entry = cursor.next();
            //分离出点赞两者id和状态
            String key = (String)entry.getKey();
            String[] splits = key.split("::");
            String likedUserId = splits[0];
            String likedPostId = splits[1];
            String type = splits[2];
            Integer value = Integer.parseInt(entry.getValue().toString());

            //拼装
            UserLike userLike = new UserLike(Long.parseLong(likedUserId),Long.parseLong(likedPostId),Integer.parseInt(type),value);
            list.add(userLike);

            //存到list后从redis中删除
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED,key);
        }
        return list;
    }

    /**
     * 获取Redis中缓存的所有点赞的数量
     * @return
     */
    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while(cursor.hasNext()){
            Map.Entry<Object,Object> entry = cursor.next();
            //分离、拼装
            String key = (String)entry.getKey();
            String[] splits = key.split("::");
            Long likedUserId = Long.parseLong(splits[0]);
            Integer type = Integer.parseInt(splits[1]);
            Integer count = Integer.parseInt(entry.getValue().toString());
            LikedCountDTO likedCountDTO = new LikedCountDTO(likedUserId,count,type);
            //存入、删除
            list.add(likedCountDTO);
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,key);
        }
        return list;
    }
}
