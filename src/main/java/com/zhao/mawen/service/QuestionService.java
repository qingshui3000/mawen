package com.zhao.mawen.service;

import com.zhao.mawen.controller.dto.PageDTO;
import com.zhao.mawen.controller.dto.QuestionDTO;
import com.zhao.mawen.dao.model.Question;

import java.util.List;

public interface QuestionService {
    PageDTO list(String search,Integer start,Integer size);

    PageDTO list(Integer start, Integer size, Long id);

    QuestionDTO getById(Long id);

    void createOrUpdate(Question question);

    void inView(String id);

    List<QuestionDTO> selectRelated(QuestionDTO queryDTO);

    List<QuestionDTO> selectHot();

    void update(QuestionDTO questionDTO);
}
