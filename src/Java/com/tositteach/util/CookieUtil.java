package com.tositteach.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    private String path;
    private int time;

    public void set(HttpServletResponse response, String name, String value, int time) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(time); //one month
        response.addCookie(cookie);
    }

    public void set(HttpServletResponse response, String name, String value) {
        set(response, name, value, time);
    }

    public void del(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public String get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Required
    public void setPath(String path) {
        this.path = path;
    }

    @Required
    public void setTime(int time) {
        this.time = time;
    }
}
