package com.tositteach.service.impl;

import com.tositteach.domain.mapper.*;
import com.tositteach.domain.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserTestService {
    @Resource
    private UserMapper dao;

    public User getUser(String id) {
        return (User) dao.selectUserById(id);
    }

}
