package com.zhao.mawen.mapper;


import com.zhao.mawen.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    void insert(Question question);

    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    Integer count();
}
