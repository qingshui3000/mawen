package com.zhao.mawen.controller;

import com.zhao.mawen.dto.PageDTO;
import com.zhao.mawen.dto.QuestionDTO;
import com.zhao.mawen.mapper.UserMapper;
import com.zhao.mawen.model.User;
import com.zhao.mawen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "start",defaultValue = "1") Integer start,
                        @RequestParam(value = "size",defaultValue = "10") Integer size) {
        Cookie[] cookies = request.getCookies();
        User user = new User();
        if(cookies != null && cookies.length != 0){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        System.out.println("uid:"+user.getId());
        PageDTO pagination = questionService.list(start,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
