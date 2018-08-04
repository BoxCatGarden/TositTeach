package com.tositteach.server.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//*.html
public class StaticPageFilter implements Filter {
    private String signInPage;
    private String homePage;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String uri = request.getRequestURI();
        String suffix = uri.substring(uri.lastIndexOf(".")+1);
        if (suffix.equals("html") || uri.equals("/")) {
            boolean isSignIn = uri.equals(signInPage) || uri.equals("/");
            if (user != null && isSignIn)
                //已登录，访问登录页面跳转到主页
                response.sendRedirect(homePage);
            else if (user == null && !isSignIn)
                //未登录，访问其他页面跳转到登录页面
                response.sendRedirect(signInPage);
            else filterChain.doFilter(servletRequest, servletResponse);
        } else filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        signInPage = filterConfig.getInitParameter("signin");
        if (signInPage == null) signInPage = "/signin.html";
        homePage = filterConfig.getInitParameter("home");
        if (homePage == null) homePage = "/home.html";
    }

    @Override
    public void destroy() {

    }
}
