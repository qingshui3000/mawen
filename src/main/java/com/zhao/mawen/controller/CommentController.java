package com.zhao.mawen.controller;

import com.zhao.mawen.common.enums.CommentTypeEnum;
import com.zhao.mawen.common.enums.ExceptionErrorCode;
import com.zhao.mawen.controller.dto.CommentCreateDTO;
import com.zhao.mawen.controller.dto.CommentDTO;
import com.zhao.mawen.controller.dto.ResultDTO;
import com.zhao.mawen.dao.model.Comment;
import com.zhao.mawen.dao.model.User;
import com.zhao.mawen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object comment(@RequestBody CommentCreateDTO commentDTO,
                          HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(ExceptionErrorCode.NO_LOGIN);
        }
        if(commentDTO == null || StringUtils.isEmpty(commentDTO.getContent())){
            return ResultDTO.errorOf(ExceptionErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setType(commentDTO.getType());
        comment.setCommentor(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO comment(@PathVariable("id")String id, Model model,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Long curUserId = 0l;
        if(user != null){
            curUserId = user.getId();
        }
        List<CommentDTO> list = commentService.listByTargetId(Long.valueOf(id), CommentTypeEnum.COMMENT, curUserId);
        return ResultDTO.okOf(list);
    }
}
