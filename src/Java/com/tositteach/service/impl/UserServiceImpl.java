package com.tositteach.service.impl;

import com.tositteach.domain.mapper.UserMapper;
import com.tositteach.domain.entity.User;
import com.tositteach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User queryById(String Id) {
        List<User> users = queryAllUser();
        for(User oneUser : users){
            if(oneUser.getUserId().equals(Id)){
                return oneUser;
            }
        }
        return null;
    }

}
