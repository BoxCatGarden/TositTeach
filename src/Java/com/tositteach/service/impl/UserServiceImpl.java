package com.tositteach.service.impl;
import com.tositteach.domain.entity.User;

import com.tositteach.domain.mapper.UserMapper;
import com.tositteach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAllUsers();
    }

    @Override
    public User queryById(String Id,String Pwd) {
        List<User> users = queryAllUser();
        for(User oneUser : users){
            if(oneUser.getUserId().equals(Id)){
                if(oneUser.getUserPwd().equals(Pwd))
                {
                    return oneUser;}
            }
        }
        return null;
    }

    @Override
    public String addUser(){
        User user = new User();
        user.setUserPwd("123");
       /* user.setUserId(UUIDUtil.getUUID());*/
        getRightId(user);
        user.setUserType((byte) 1);
        Integer res = userMapper.insertUser(user);
        return user.getUserId();
    }


    @Override
    public Integer modifyUserPwd(User user){
        return userMapper.updateUserPwd(user);
    }

    @Override
    public Integer cutUserById(String userId){return userMapper.deleteUserById(userId);}

    @Override
    public void getRightId(User user) {
        List<User> users = userMapper.selectAllUsers();
        if(users.size()==0){
            user.setUserId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (User user1 : users) {
                iid.add(new Integer(user1.getUserId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;
            System.out.println(id1 + "\n\n\n\n\n\n");
            String id = String.valueOf(id1);
            user.setUserId(id);
        }

    }


}
