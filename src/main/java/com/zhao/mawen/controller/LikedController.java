package com.zhao.mawen.controller;

import com.zhao.mawen.dto.ResultDTO;
import com.zhao.mawen.model.UserLike;
import com.zhao.mawen.service.LikedService;
import com.zhao.mawen.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LikedController {
    @Autowired
    private RedisService redisService;

    @PostMapping("/liked")
    @ResponseBody
    public ResultDTO liked(@RequestBody UserLike userLike){
        String likedUserId = userLike.getLikedUserId().toString();
        String likedPostId = userLike.getLikedPostId().toString();
        Integer type = userLike.getType();
        redisService.saveLiked2Redis(likedUserId, likedPostId, type);
        redisService.incrementLikedCount(likedUserId,type);
        return ResultDTO.okOf();
    }

    @PostMapping("/unliked")
    @ResponseBody
    public ResultDTO unlike(@RequestBody UserLike userLike){
        String likedUserId = userLike.getLikedUserId().toString();
        String likedPostId = userLike.getLikedPostId().toString();
        Integer type = userLike.getType();
        redisService.unlikedFromRedis(likedUserId, likedPostId, type);
        redisService.decrementLikedCount(likedUserId,type);
        return ResultDTO.okOf();
    }
}
