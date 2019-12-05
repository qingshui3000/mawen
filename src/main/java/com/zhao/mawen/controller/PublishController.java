package com.zhao.mawen.controller;

import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.mapper.QuestionMapper;
import com.zhao.mawen.model.Question;
import com.zhao.mawen.model.User;
import com.zhao.mawen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id")Integer id,
                       HttpServletRequest request,
                       Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        User user = (User)request.getSession().getAttribute("user");
        if(user != null && user.getId().equals(questionDTO.getCreator())){
            model.addAttribute("title",questionDTO.getTitle());
            model.addAttribute("description",questionDTO.getDescription());
            model.addAttribute("tag",questionDTO.getTag());
            model.addAttribute("id",id);
        }else{
            return "error";
        }
        return "/publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false)String title,
            @RequestParam(value = "description",required = false)String description,
            @RequestParam(value = "tag",required = false)String tag,
            @RequestParam("id") Integer id,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        if(title == null || "".equals(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || "".equals(description)){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(tag == null || "".equals(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
