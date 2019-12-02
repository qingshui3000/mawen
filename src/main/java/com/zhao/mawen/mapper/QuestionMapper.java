package com.zhao.mawen.mapper;


import com.zhao.mawen.model.Question;

import java.util.List;

public interface QuestionMapper {
    void insert(Question question);

    List<Question> list();
}
