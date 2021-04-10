package com.zhao.mawen.service.impl;

import com.zhao.mawen.common.enums.LikedTargetTypeEnum;
import com.zhao.mawen.controller.dto.CommentDTO;
import com.zhao.mawen.controller.dto.LikedCountDTO;
import com.zhao.mawen.controller.dto.QuestionDTO;
import com.zhao.mawen.dao.mapper.UserLikeMapper;
import com.zhao.mawen.dao.model.User;
import com.zhao.mawen.dao.model.UserLike;
import com.zhao.mawen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LikedServiceImpl implements LikedService {
    @Autowired
    private UserLikeMapper userLikeMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    /**
     * 保存点赞记录到MySQL
     * @param userLike
     * @return
     */
    @Override
    @Transactional
    public void save(UserLike userLike) {
        userLikeMapper.insert(userLike);
    }

    /**
     * 批量保存
     * @param list
     * @return
     */
    @Override
    @Transactional
    public void saveAll(List<UserLike> list) {
        userLikeMapper.insertAll(list);
    }

    /**
     * 通过点赞项目和点赞人的id查询是否存在 点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    @Override
    public UserLike getByLikedUserIdAndLikedPostId(Long likedUserId, Long likedPostId,Integer type) {
        return userLikeMapper.find(likedUserId,likedPostId,type);
    }

    /**
     * 将redis中的点赞记录刷新到MySQL
     */
    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<UserLike> list = redisService.getLikedDataFromRedis();
        for(UserLike like : list){
            //防止注入
            if(userService.isNullById(like.getLikedPostId())){
                continue;
            }
            UserLike ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(),like.getLikedPostId(),like.getType());
            if(ul == null){
                //没有记录，存入
                save(like);
            }else{
                //有记录，更新（可能取消了或者取消又点赞了）
                ul.setStatus(like.getStatus());
                userLikeMapper.update(ul);
            }
        }
    }

    /**
     * 将redis中的点赞数量存入MySQL
     */
    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
        for(LikedCountDTO likedCountDTO : list){
            Integer type = likedCountDTO.getType();
            //点赞问题时，更新问题和提问人的赞数
            if(LikedTargetTypeEnum.Question.getCode().equals(type)){
                QuestionDTO questionDTO = questionService.getById(likedCountDTO.getId());
                if(questionDTO != null){
                    Integer count = likedCountDTO.getCount();
                    Integer cntQ = questionDTO.getLikeCount() + count;
                    questionDTO.setLikeCount(cntQ);
                    User user = questionDTO.getUser();
                    if(user != null){
                        Integer cntU = user.getLikeCount() + count;
                        user.setLikeCount(cntU);
                        questionDTO.setUser(user);
                    }
                    questionService.update(questionDTO);
                }

            }
            //点赞评论时，更新这条评论和评论人的赞数
            if(LikedTargetTypeEnum.Comment.getCode().equals(type)){
                CommentDTO commentDTO = commentService.getById(likedCountDTO.getId());
                if(commentDTO != null){
                    Integer count = likedCountDTO.getCount();
                    Integer cntC = commentDTO.getLikeCount() + count;
                    commentDTO.setLikeCount(cntC);
                    User user = commentDTO.getUser();
                    if(user != null){
                        Integer cntU = user.getLikeCount() + count;
                        user.setLikeCount(cntC);
                        commentDTO.setUser(user);
                    }
                    commentService.update(commentDTO);
                }
            }
        }
    }

    /**
     * 查询当前用户是否给某条评论或问题点过赞
     * @param likedUserId
     * @param likedPostId
     * @param type
     * @return
     */
    @Override
    public boolean hasLiked(Long likedUserId, Long likedPostId, Integer type) {
        UserLike ul = userLikeMapper.find(likedUserId,likedPostId,type);
        if(ul == null){
            return false;
        }
        return ul.getStatus() == 1;
    }

    public static Date getSystemTime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.parse(simpleDateFormat.format(date));
    }
}
