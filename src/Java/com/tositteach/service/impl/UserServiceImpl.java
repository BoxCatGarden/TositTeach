package com.tositteach.service.impl;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Student;
import com.tositteach.domain.entity.User;

import com.tositteach.domain.mapper.UserMapper;
import com.tositteach.service.UserService;
import com.tositteach.util.Md5Encryptor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User signIn(String userId, String pwd) {
        String p = userMapper.getPwd(userId);
        if (p != null && p.equals(Md5Encry(pwd))) {
            return userMapper.getUser(userId);
        }
        return null;
    }

    @Override
    public int changePwd(String userId, String op, String np) {
        return userMapper.setPwd(userId, Md5Encry(op), Md5Encry(np));
    }

    @Override
    public Student getStuUserInfo(String userId) {
        return userMapper.getStuInfo(userId);
    }

    @Override
    public Engineer getEngUserInfo(String userId) {
        return userMapper.getEngInfo(userId);
    }


    private String Md5Encry(String p) {
        return Md5Encryptor.encrypt(p);
    }
}
