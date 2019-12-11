package com.zhao.mawen.mapper;

import com.zhao.mawen.enums.CommentTypeEnum;
import com.zhao.mawen.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> queryByQuestionId(@Param("questionId") Long id,@Param("type") Integer type);

    List<Comment> queryByCommondId(@Param("commentId") Long id,@Param("type") Integer type);

    void incCommentCount(Comment dbComment);
}