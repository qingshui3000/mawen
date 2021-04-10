package com.zhao.mawen.service.impl;

import com.zhao.mawen.controller.dto.CommentDTO;
import com.zhao.mawen.controller.dto.LikedCountDTO;
import com.zhao.mawen.common.enums.CommentTypeEnum;
import com.zhao.mawen.common.enums.LikedTargetTypeEnum;
import com.zhao.mawen.common.enums.ExceptionErrorCode;
import com.zhao.mawen.controller.dto.MawenException;
import com.zhao.mawen.dao.mapper.CommentMapper;
import com.zhao.mawen.dao.mapper.QuestionMapper;
import com.zhao.mawen.dao.mapper.UserMapper;
import com.zhao.mawen.dao.model.Comment;
import com.zhao.mawen.dao.model.Question;
import com.zhao.mawen.dao.model.User;
import com.zhao.mawen.service.CommentService;
import com.zhao.mawen.service.LikedService;
import com.zhao.mawen.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LikedService likedService;
    @Autowired
    private RedisService redisService;
    @Transactional
    @Override
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() <= 0){
            throw new MawenException(ExceptionErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new MawenException(ExceptionErrorCode.TYPE_PARAM_NOT_FOUND);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.findById(comment.getParentId());
            if(dbComment == null){
                throw new MawenException(ExceptionErrorCode.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.findById(dbComment.getParentId());
            if(question == null){
                throw new MawenException(ExceptionErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //增加二级回复的数量
            dbComment.setGmtModified(System.currentTimeMillis());
            commentMapper.incCommentCount(dbComment);
        }else{
            //回复问题
            Question question = questionMapper.findById(comment.getParentId());
            if(question == null){
                throw new MawenException(ExceptionErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.incCommentCount(question);
        }
    }

    @Override
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type,Long curUserId) {
        List<Comment> comments;
        //获取评论集合
        if(type.getType() == 1){
            comments = commentMapper.queryByQuestionId(id,type.getType());
        }else{
            comments = commentMapper.queryByCommondId(id,type.getType());
        }
        if(comments.size() == 0){
            return new ArrayList<>();
        }
        System.out.println(comments.toString());
        //根据评论集合获取去重的用户id集合
        Set<Long> commentors = comments.stream().map(comment -> comment.getCommentor()).collect(Collectors.toSet());
        //mybatis批量查询用户集合
        List<User> users = userMapper.findByIds(commentors);
        //用户集合和id转map
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //缓存的点赞数目集合
        List<LikedCountDTO> likedCountDTOList = redisService.getLikedCountFromRedis();
        //组成结果返回
        List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentor()));
            if(curUserId != 0){
                //若已登录，查询是否点赞这条回复了
                boolean isLiked = likedService.hasLiked(comment.getId(), curUserId, LikedTargetTypeEnum.Comment.getCode());
                commentDTO.setLiked(isLiked);
            }
//            //从redis缓存中寻找存在但未刷新的
            LikedCountDTO likedCountDTO = new LikedCountDTO(comment.getId(),LikedTargetTypeEnum.Comment.getCode());
            int newCount = comment.getLikeCount();
            if(likedCountDTOList.contains(likedCountDTO)){
                newCount += likedCountDTOList.get(likedCountDTOList.indexOf(likedCountDTO)).getCount();
            }
            commentDTO.setLikeCount(newCount);
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOs;
    }

    @Override
    public CommentDTO getById(Long id) {
        Comment comment = commentMapper.findById(id);
        if(comment == null){
            throw new MawenException(ExceptionErrorCode.COMMENT_NOT_FOUND);
        }
        User user = userMapper.findById(comment.getCommentor());
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(comment,commentDTO);
        commentDTO.setUser(user);
        return commentDTO;
    }

    @Override
    public void update(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        User user = commentDTO.getUser();
        userMapper.update(user);
        commentMapper.update(comment);
    }
}
