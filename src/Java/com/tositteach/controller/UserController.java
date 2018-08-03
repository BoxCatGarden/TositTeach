package com.tositteach.controller;

import com.tositteach.domain.entity.Gp;
import com.tositteach.domain.entity.Project;
import com.tositteach.domain.entity.Student;
import com.tositteach.domain.entity.User;
import com.tositteach.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    private Map<String,HttpSession> userMap = new ConcurrentHashMap<>(); //multi-thread!!!

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public int signIn(@RequestBody UserReqBody req, HttpServletRequest request) { //ui,p
        if (req.ui==null||req.p==null) return 0;
        User user = userService.signIn(req.ui,req.p); //try to sign in
        if (user != null) { //succeed
            HttpSession session = userMap.get(user.getUserId());
            if (session != null) session.removeAttribute("user"); //remove the current signed session
            session = request.getSession();
            session.setAttribute("user", user); //sign in the session
            session.setAttribute("ui", user.getUserId());
            userMap.put(user.getUserId(),session);
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    @ResponseBody
    public int signOut(HttpSession session) {
        if (session.getAttribute("user") != null) {
            userMap.remove((String)session.getAttribute("ui"));
            session.removeAttribute("user");
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/chgpwd", method = RequestMethod.POST)
    @ResponseBody
    public int changePwd(@RequestBody UserReqBody req, HttpSession session) {
        if (req.p==null||req.np==null)return 0;
        User user = (User) session.getAttribute("user");
        return userService.changePwd(user.getUserId(), req.p, req.np);
    }

    //获取用户简报（id,name,type）
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public User head(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    //获取用户信息，智能接口，根据用户类型不同自动返回不同的用户信息
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object info(HttpSession session) {
        User user = (User) session.getAttribute("user");
        byte type = user.getType();
        if (type == 1) {
            return userService.getStuUserInfo(user.getUserId());
        } else if (type == 2) {
            return userService.getEngUserInfo(user.getUserId());
        } else return null;
    }

}

class UserReqBody {
    String ui;
    String p;
    String np;

    public void setUi(String ui) {
        this.ui = ui;
    }

    public void setP(String p) {
        this.p = p;
    }

    public void setNp(String np) {
        this.np = np;
    }
}
