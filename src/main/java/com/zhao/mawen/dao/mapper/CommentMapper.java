package com.zhao.mawen.dao.mapper;

import com.zhao.mawen.dao.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    int insert(Comment record);

    Comment findById(Long id);

    int update(Comment record);

    List<Comment> queryByQuestionId(@Param("questionId") Long id,@Param("type") Integer type);

    List<Comment> queryByCommondId(@Param("commentId") Long id,@Param("type") Integer type);

    void incCommentCount(Comment dbComment);
}