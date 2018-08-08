package com.tositteach.server.filter;

import com.tositteach.controller.UserController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValiInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getSession().getAttribute(UserController.USER) != null) {
            if (request.getRequestURI().equals("/in/user/signin")) {
                XHRRedirect(response, "/mainpage.html");
                return false;
            } else return true;
        } else if (!request.getRequestURI().equals("/in/user/signin")) {
            XHRRedirect(response, "/signin.html");
            return false;
        } else return true;
    }

    private void XHRRedirect(HttpServletResponse response, String location) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(399);
        try {
            response.getOutputStream().write(("\""+location+"\"").getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
