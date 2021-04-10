package com.zhao.mawen.service;

import com.zhao.mawen.common.enums.CommentTypeEnum;
import com.zhao.mawen.controller.dto.CommentDTO;
import com.zhao.mawen.dao.model.Comment;

import java.util.List;

public interface CommentService {
    void insert(Comment comment);

    List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type,Long curUserId);

    CommentDTO getById(Long id);

    void update(CommentDTO commentDTO);
}
