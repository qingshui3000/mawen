package com.zhao.mawen.service.impl;

import com.zhao.mawen.dao.mapper.UserMapper;
import com.zhao.mawen.dao.model.User;
import com.zhao.mawen.common.provider.EncryptionProvider;
import com.zhao.mawen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkOrCreate(User user){
        try {
            String base64Account = EncryptionProvider.base64(user.getAccount());

            User dbUser = userMapper.findByAccount(base64Account);
            if(dbUser == null){
                //create
                dbUser = new User();
                dbUser.setName(user.getName());
                dbUser.setAccount(base64Account);
                dbUser.setPassword(EncryptionProvider.base64(user.getPassword()));
                dbUser.setGmtCreate(System.currentTimeMillis());
                dbUser.setGmtModified(dbUser.getGmtCreate());
                dbUser.setToken(UUID.randomUUID().toString());
                userMapper.insert(dbUser);
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean check(User user) {
        try {
            String base64Account = EncryptionProvider.base64(user.getAccount());
            String base64Password = EncryptionProvider.base64(user.getPassword());
            User dbUser = userMapper.find(base64Account,base64Password);
            if(dbUser != null){
                user.setToken(dbUser.getToken());
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isOnlyName(String name){
        if(userMapper.findByName(name) == null){
            return true;
        }
        return false;
    }


    @Override
    public void update(User user) {
    }

    @Override
    public User getForLiked(Long id, Integer type) {
        return null;
    }

    @Override
    public boolean isNullById(Long likedPostId) {
        User user = userMapper.findById(likedPostId);
        if(user == null){
            return true;
        }
        return false;
    }

    @Override
    public void changeOrCreateFace(User user) {

    }
}
