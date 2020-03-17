package com.zhao.mawen.controller;

import com.zhao.mawen.dto.CommentDTO;
import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.enums.CommentTypeEnum;
import com.zhao.mawen.model.User;
import com.zhao.mawen.service.CommentService;
import com.zhao.mawen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String detail(@PathVariable(name = "id") String id,
                         Model model, HttpServletRequest request){
        questionService.inView(id);
        User user = (User)request.getSession().getAttribute("user");
        Long curUserId = 0l;
        if(user != null){
            curUserId = user.getId();
        }
        QuestionDTO questionDTO = questionService.getById(Long.valueOf(id));
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(Long.valueOf(id), CommentTypeEnum.QUESTION,curUserId);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        System.out.println(relatedQuestions.toString());
        return "detail";
    }



}
