package com.zhao.mawen.service;

import com.zhao.mawen.dao.model.User;

public interface UserService {
    boolean checkOrCreate(User user);

    boolean check(User user) ;

    boolean isOnlyName(String name);


    void update(User user);

    User getForLiked(Long id, Integer type);

    boolean isNullById(Long likedPostId);

    void changeOrCreateFace(User user);
}
