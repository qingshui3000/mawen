package com.zhao.mawen.service;

import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.mapper.QuestionMapper;
import com.zhao.mawen.mapper.UserMapper;
import com.zhao.mawen.model.Question;
import com.zhao.mawen.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            System.out.println(questionDTO.toString());
            list.add(questionDTO);
        }
        return list;
    }
}
