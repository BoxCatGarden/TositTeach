package com.tositteach.service;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Student;
import com.tositteach.domain.entity.User;

import java.util.List;

public interface UserService {
    User signIn(String userId, String pwd);
    int changePwd(String userId, String op, String np);
    Student getStuUserInfo(String userId);
    Engineer getEngUserInfo(String userId);
}