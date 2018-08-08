package com.tositteach.server.listener;

import com.tositteach.controller.UserController;
import com.tositteach.domain.entity.User;

import javax.servlet.http.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserSessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private Map<String, HttpSession> userMap = new ConcurrentHashMap<>(); //multi-thread!!!

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user = (User) session.getAttribute(UserController.USER);
        if (user != null) {
            session.removeAttribute(UserController.USER);
            userMap.remove(user.getUserId());
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals(UserController.USER)) {
            String userId = ((User) event.getValue()).getUserId();
            HttpSession session = userMap.get(userId);
            if (session != null) session.removeAttribute(UserController.USER);
            userMap.put(userId, event.getSession());
        }
    }


    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equals(UserController.USER)) {
            event.getSession().removeAttribute(UserController.USER);
            userMap.remove(((User) event.getValue()).getUserId());
        }
    }

}
