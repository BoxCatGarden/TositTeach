package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> selectAllUsers();

    public Integer insertUser(User user);

    public Integer updateUserPwd(User user);

    public Integer deleteUserById(@Param("userId") String userId);
}
