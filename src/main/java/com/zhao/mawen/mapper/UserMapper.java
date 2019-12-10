package com.zhao.mawen.mapper;

import com.zhao.mawen.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserMapper {
    void insert(User user);

    User findByToken(String token);

    User findById(Integer creator);

    User findByAccountId(String accountId);

    void update(User user);

    List<User> findByIds(@Param("ids") Set<Integer> commentors);
}
