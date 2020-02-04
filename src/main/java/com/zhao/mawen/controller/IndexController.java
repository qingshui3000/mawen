package com.zhao.mawen.controller;

import com.zhao.mawen.dto.PageDTO;
import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String hello(
            Model model,
            @RequestParam(value = "search",defaultValue = "")String search,
            @RequestParam(value = "start",defaultValue = "1") Integer start,
            @RequestParam(value = "size",defaultValue = "10") Integer size) {
        PageDTO pagination = questionService.list(start,size);
        List<QuestionDTO> hotQuestions = questionService.selectHot();
        model.addAttribute("pagination",pagination);
        model.addAttribute("hotQuestions",hotQuestions);
        return "index";
    }
}
