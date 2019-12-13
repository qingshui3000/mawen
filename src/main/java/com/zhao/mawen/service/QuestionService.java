package com.zhao.mawen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mawen.dto.PageDTO;
import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.exception.ExceptionErrorCode;
import com.zhao.mawen.exception.MawenException;
import com.zhao.mawen.mapper.QuestionMapper;
import com.zhao.mawen.mapper.UserMapper;
import com.zhao.mawen.model.Question;
import com.zhao.mawen.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PageDTO list(Integer start,Integer size) {
        PageHelper.startPage(start,size);
        List<Question> questions = questionMapper.list();
        PageDTO pageDTO = getPage(start,size,questions);
        return pageDTO;
    }

    public PageDTO list(Integer start, Integer size, Integer id) {
        PageHelper.startPage(start,size);
        List<Question> questions = questionMapper.listById(id);
        PageDTO pageDTO = getPage(start,size,questions);
        return  pageDTO;
    }

    private PageDTO getPage(Integer start, Integer size, List<Question> questions) {
        PageDTO pageDTO = new PageDTO();
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

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.findById(id);
        if(question == null){
            throw new MawenException(ExceptionErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        Question dbQuestion = questionMapper.findById(question.getId());
        if(dbQuestion == null){
            //insert
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setViewCount(0);
            questionMapper.insert(question);
        }else{
            //update
            question.setGmtModified(System.currentTimeMillis());
            int update = questionMapper.update(question);
            if(update < 1){
                throw new MawenException(ExceptionErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void inView(String id) {
        Question question = questionMapper.findById(Long.valueOf(id));
        question.setViewCount(question.getViewCount() + 1);
        questionMapper.update(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO){
        Question question = new Question();
        question.setId(queryDTO.getId());
        String regexTag = queryDTO.getTag().replaceAll(",","|");
        System.out.println("tags:"+regexTag);
        question.setTag(regexTag);
        List<Question> questions = questionMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q->{
           QuestionDTO questionDTO = new QuestionDTO();
           BeanUtils.copyProperties(q,questionDTO);
           return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
