package com.zhao.mawen.controller;

import com.zhao.mawen.dto.CommentDTO;
import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.enums.CommentTypeEnum;
import com.zhao.mawen.service.CommentService;
import com.zhao.mawen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String detail(@PathVariable(name = "id") String id,
                         Model model){
        questionService.inView(id);
        QuestionDTO questionDTO = questionService.getById(Long.valueOf(id));
        List<CommentDTO> list = commentService.listByTargetId(Long.valueOf(id), CommentTypeEnum.QUESTION);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",list);

        return "detail";
    }
}
