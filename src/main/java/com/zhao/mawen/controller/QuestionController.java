package com.zhao.mawen.controller;

import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String detail(@PathVariable(name = "id") String id,
                         Model model){
        QuestionDTO questionDTO = questionService.getById(Integer.parseInt(id));
        model.addAttribute("question",questionDTO);
        return "detail";
    }
}
