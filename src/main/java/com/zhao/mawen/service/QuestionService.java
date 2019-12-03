package com.zhao.mawen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mawen.dto.PageDTO;
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

    public PageDTO list(Integer start,Integer size) {
        PageDTO pageDTO = new PageDTO();
        PageHelper.startPage(start,size);
        List<Question> questions = questionMapper.list();
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        int totalItem = (int)pageInfo.getTotal();
        if(totalItem % size == 0){
            pageDTO.setTotal(totalItem / size);
        }else {
            pageDTO.setTotal(totalItem / size + 1);
        }
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.initPages();
        System.out.println(pageDTO.getPages().toString());
        System.out.println("total:"+pageDTO.getTotal());
        System.out.println("page:"+pageDTO.getPage());

        List<QuestionDTO> list = new ArrayList<>();
        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        pageDTO.setQuestions(list);
        return pageDTO;
    }

    public PageDTO list(Integer start, Integer size, Integer id) {
        PageDTO pageDTO = new PageDTO();
        PageHelper.startPage(start,size);
        List<Question> questions = questionMapper.listById(id);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        int totalItem = (int)pageInfo.getTotal();
        if(totalItem % size == 0){
            pageDTO.setTotal(totalItem / size);
        }else {
            pageDTO.setTotal(totalItem / size + 1);
        }
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.initPages();
        System.out.println(pageDTO.getPages().toString());
        System.out.println("total:"+pageDTO.getTotal());
        System.out.println("page:"+pageDTO.getPage());

        List<QuestionDTO> list = new ArrayList<>();
        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        pageDTO.setQuestions(list);
        return pageDTO;
    }
}