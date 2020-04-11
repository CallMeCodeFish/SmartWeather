package com.csc510.smartweather.service;

import com.csc510.smartweather.mapper.UserMapper;
import com.csc510.smartweather.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Heng Yu
 * @date 4/10/20 9:00 PM
 */

@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdateUser(User user) {
        List<User> dbUserList = userMapper.selectByAccount(user);
        String token = UUID.randomUUID().toString();
        if (dbUserList.isEmpty()) {
            user.setToken(token);
            user.setCreatedAt(System.currentTimeMillis());
            user.setUpdatedAt(user.getCreatedAt());
            userMapper.insert(user);
            dbUserList = userMapper.selectByAccount(user);
            User dbUser = dbUserList.get(0);
            BeanUtils.copyProperties(dbUser, user);
        } else {
            User dbUser = dbUserList.get(0);
            dbUser.setName(user.getName());
            dbUser.setToken(token);
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setUpdatedAt(System.currentTimeMillis());
            userMapper.update(dbUser);
            BeanUtils.copyProperties(dbUser, user);
        }
    }

    public void selectByToken(User user) {
        List<User> dbUserList = userMapper.selectByToken(user);
        //可能抛异常
        User dbUser = dbUserList.get(0);
        BeanUtils.copyProperties(dbUser, user);
    }
}
