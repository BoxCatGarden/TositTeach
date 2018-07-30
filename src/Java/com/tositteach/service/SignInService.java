package com.tositteach.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SignInService {
    int signIn(String userId, String pwd);
}
