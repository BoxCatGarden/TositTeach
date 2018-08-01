package com.tositteach.service;
import com.tositteach.domain.entity.User;

import java.util.List;

public interface UserService {
    public List<User> queryAllUser();
    public User queryById(String Id, String Pwd);
    public String addUser();
    public Integer modifyUserPwd(User user);
    public Integer cutUserById(String userId);
    public void getRightId(User user);
}