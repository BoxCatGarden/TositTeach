package com.tositteach.server.filter;

import com.tositteach.service.SignInService;
import com.tositteach.util.CookieUtil;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValiInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private CookieUtil util;
    @Resource
    private SignInService signInService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       HttpSession session = request.getSession();
       if (session == null) {
           String userId = util.get(request, "ui");
           String pwd = util.get(request, "p");
           if (userId == null || pwd == null || signInService.signIn(userId, util.get(request,"p")) != 1) {
               util.del(response, "ui");
               util.del(response, "p");
               response.sendRedirect("/signin.html");
               return false;
           }
       }
       return true;
    }

}
