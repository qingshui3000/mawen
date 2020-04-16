package com.zhao.mawen.controller;

import com.zhao.mawen.dto.PageDTO;
import com.zhao.mawen.dto.ResultDTO;
import com.zhao.mawen.model.User;
import com.zhao.mawen.provider.CosProvider;
import com.zhao.mawen.service.QuestionService;
import com.zhao.mawen.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Value("${qcloud.cos.secret.id}")
    public String secretId;
    @Value("${qcloud.cos.secret.key}")
    public String secretKey;
    @Value("${qcloud.cos.bucket.name}")
    public String bucketName;
    @Value("${qcloud.cos.region}")
    public String region;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "start",defaultValue = "1") Integer start,
                          @RequestParam(value = "size",defaultValue = "10") Integer size){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }else if("selfish".equals(action)){
            model.addAttribute("section","selfish");
            model.addAttribute("sectionName","我的信息");
        }

        PageDTO pagination = questionService.list(start,size,user.getId());
        model.addAttribute("pagination",pagination);
        return "profile";
    }

    @PostMapping("/upface")
    public void upFace(){

    }

    @PostMapping("/putface")
    @ResponseBody
    public ResultDTO getTmpKey(@RequestBody String file){
        System.out.println(file);
        return  ResultDTO.okOf();
    }
}
