package com.zhao.mawen.mapper;

import com.zhao.mawen.model.User;

public interface UserMapper {
    void insert(User user);
    User findByToken(String token);
}
