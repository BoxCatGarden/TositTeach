package com.tositteach.service;

import com.tositteach.domain.entity.User;

import java.util.List;

public interface UserService {
    public List<User> queryAllUser();
    public User queryById(String Id);
}
