package com.zhao.mawen.service;

import com.zhao.mawen.dto.CommentDTO;
import com.zhao.mawen.enums.CommentTypeEnum;
import com.zhao.mawen.exception.ExceptionErrorCode;
import com.zhao.mawen.exception.MawenException;
import com.zhao.mawen.mapper.CommentMapper;
import com.zhao.mawen.mapper.QuestionMapper;
import com.zhao.mawen.mapper.UserMapper;
import com.zhao.mawen.model.Comment;
import com.zhao.mawen.model.Question;
import com.zhao.mawen.model.User;
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
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() <= 0){
            throw new MawenException(ExceptionErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new MawenException(ExceptionErrorCode.TYPE_PARAM_NOT_FOUND);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new MawenException(ExceptionErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question question = questionMapper.findById(comment.getParentId());
            if(question == null){
                throw new MawenException(ExceptionErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.incCommentCount(question);
        }
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
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
        Set<Integer> commentors = comments.stream().map(comment -> comment.getCommentor()).collect(Collectors.toSet());
        //mybatis批量查询用户集合
        List<User> users = userMapper.findByIds(commentors);
        //用户集合和id转map
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //组成结果返回
        List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentor()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOs;
    }
}
