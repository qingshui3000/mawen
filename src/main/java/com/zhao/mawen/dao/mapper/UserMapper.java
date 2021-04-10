package com.zhao.mawen.dao.mapper;

import com.zhao.mawen.dao.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
