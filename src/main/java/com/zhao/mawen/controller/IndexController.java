package com.zhao.mawen.controller;

import com.zhao.mawen.controller.dto.PageDTO;
import com.zhao.mawen.controller.dto.QuestionDTO;
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
            @RequestParam(name = "search",required = false)String search,
            @RequestParam(name = "start",defaultValue = "1") Integer start,
            @RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageDTO pagination = questionService.list(search,start,size);
        List<QuestionDTO> hotQuestions = questionService.selectHot();
        model.addAttribute("pagination",pagination);
        model.addAttribute("hotQuestions",hotQuestions);
        return "index";
    }

}
