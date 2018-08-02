package com.tositteach.server.listener;

import com.tositteach.controller.UserController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener,ServletContextListener {

    private WebApplicationContext springCtx;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        springCtx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //DO NOTHING
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //DO NOTHING
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ((UserController) springCtx.getBean(UserController.class)).signOut(se.getSession());
    }
}
