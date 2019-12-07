package com.zhao.mawen.service;

import com.zhao.mawen.exception.ExceptionErrorCode;
import com.zhao.mawen.exception.MawenException;
import com.zhao.mawen.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() <= 0){
            throw new MawenException(ExceptionErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
