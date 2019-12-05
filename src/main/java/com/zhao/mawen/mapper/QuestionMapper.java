package com.zhao.mawen.mapper;

import com.zhao.mawen.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    void insert(Question question);

    List<Question> list();

    Integer count();

    List<Question> listById(@Param("creator") Integer creator);

    Question findById(Integer id);

    int update(Question question);
}
