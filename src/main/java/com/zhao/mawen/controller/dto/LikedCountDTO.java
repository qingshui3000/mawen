package com.zhao.mawen.controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 点赞数量的DTO，用于存储从Redis取出来的点赞数
 */
@Data
public class LikedCountDTO implements Serializable {
    private static final long serialVersionUID = -2856160546081194945L;
    private Long id;
    private Integer count;
    private Integer type;

    public LikedCountDTO() {

    }

    public LikedCountDTO(Long id, Integer count,Integer type) {
        this.id = id;
        this.count = count;
        this.type = type;
    }

    public LikedCountDTO(Long id, Integer type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        LikedCountDTO likedCountDTO = (LikedCountDTO)o;
        if(likedCountDTO.getId() == this.getId() && likedCountDTO.getType() == this.getType()){
            return true;
        }
        return false;
    }
}
