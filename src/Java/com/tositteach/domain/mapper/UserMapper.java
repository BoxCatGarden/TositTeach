package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.User;

public interface UserMapper {
    public User selectUserById(String id);
}
