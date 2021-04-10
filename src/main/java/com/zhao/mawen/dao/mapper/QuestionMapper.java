package com.zhao.mawen.dao.mapper;

import com.zhao.mawen.dao.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    void insert(Question question);

    List<Question> list();

    List<Question> list(@Param("keyword") String search);

    Integer count();

    List<Question> listById(@Param("creator") Long creator);

    Question findById(Long id);

    int update(Question question);

    void incCommentCount(Question question);

    List<Question> selectRelated(Question question);

    List<Question> selectHot();
}
