package com.zhao.mawen.mapper;

import com.zhao.mawen.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserMapper {
    void insert(User user);

    User findByToken(String token);

    User findById(Long id);

    User findByAccountId(String accountId);

    void update(User user);

    List<User> findByIds(@Param("ids") Set<Long> commentors);

    User findByAccount(String base64);

    User find(@Param("account") String account, @Param("password") String password);

    User findByName(String name);
}
