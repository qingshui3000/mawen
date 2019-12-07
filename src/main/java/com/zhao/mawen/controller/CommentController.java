package com.zhao.mawen.controller;

import com.zhao.mawen.dto.CommentDTO;
import com.zhao.mawen.dto.ResultDTO;
import com.zhao.mawen.exception.ExceptionErrorCode;
import com.zhao.mawen.exception.MawenException;
import com.zhao.mawen.mapper.CommentMapper;
import com.zhao.mawen.model.Comment;
import com.zhao.mawen.model.User;
import com.zhao.mawen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(ExceptionErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParaentId());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setType(commentDTO.getType());
        comment.setCommentor(16);
        commentService.insert(comment);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("message","success");
        resultMap.put("type",1);
        return ResultDTO.okOf();
    }
}
