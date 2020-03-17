package com.zhao.mawen.controller;

import com.zhao.mawen.dto.ResultDTO;
import com.zhao.mawen.dto.UserDTO;
import com.zhao.mawen.exception.ExceptionErrorCode;
import com.zhao.mawen.model.User;
import com.zhao.mawen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @PostMapping("/login")
    public ResultDTO login(@RequestBody User user,
                           HttpServletResponse response){
        if(StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())){
            return ResultDTO.errorOf(ExceptionErrorCode.LOGIN_INPUT_EMPTY);
        }
        if(userService.check(user)){
            response.addCookie(new Cookie("token",user.getToken()));
            return ResultDTO.okOf(200,"登陆成功！");
        }
        return ResultDTO.errorOf(5001,"账户或密码错误！");
    }

    @ResponseBody
    @PostMapping("/reg")
    public ResultDTO reg(@RequestBody User user){
        if(StringUtils.isEmpty(user.getAccount()) ||
                StringUtils.isEmpty(user.getPassword())||
                        StringUtils.isEmpty(user.getName())){
            return ResultDTO.errorOf(ExceptionErrorCode.LOGIN_INPUT_EMPTY);
        }
        if(!userService.isOnlyName(user.getName())){
            return ResultDTO.errorOf(5001,"昵称已存在，请修改！");
        }
        if(userService.checkOrCreate(user)){
            //"注册成功"
            return ResultDTO.okOf(200,"注册成功，请登录！");
        }
        //"用户名已存在"
        return ResultDTO.errorOf(5000,"账户已存在，请修改或登录！");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                            HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
