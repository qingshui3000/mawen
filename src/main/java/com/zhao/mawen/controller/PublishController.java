package com.zhao.mawen.controller;

import com.zhao.mawen.cache.TagCache;
import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.exception.ExceptionErrorCode;
import com.zhao.mawen.exception.MawenException;
import com.zhao.mawen.model.Question;
import com.zhao.mawen.model.User;
import com.zhao.mawen.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
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
    public String edit(@PathVariable("id")Long id,
                       HttpServletRequest request,
                       Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        User user = (User)request.getSession().getAttribute("user");
        if(user != null && user.getId().equals(questionDTO.getCreator())){
            model.addAttribute("title",questionDTO.getTitle());
            model.addAttribute("description",questionDTO.getDescription());
            model.addAttribute("tag",questionDTO.getTag());
            model.addAttribute("id",id);
            model.addAttribute("tags",TagCache.get());
        }else{
            throw new MawenException(ExceptionErrorCode.NO_AUTH);
        }
        return "/publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags",TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false)String title,
            @RequestParam(value = "description",required = false)String description,
            @RequestParam(value = "tag",required = false)String tag,
            @RequestParam("id") Long id,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags",TagCache.get());
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error", ExceptionErrorCode.NO_LOGIN);
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
        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","输入了非法标签:"+invalid);
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
