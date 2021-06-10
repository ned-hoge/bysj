package com.projectssm.service;

import com.projectssm.model.User;
import com.projectssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:dingji
 * @Date: 2021/4/23 0026
 * @Time: 15:23
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Sel(int id){
        return userMapper.Sel(id);
    }

    public User login(String userName, String passWord) {
        return userMapper.login(userName,passWord);
    }

    public User getstaffidbyuser(String id){return userMapper.getstaffidbyuser(id);}

    public int register(User user) {
        return userMapper.register(user);
    }

    public int update(String state,String staffid) {
        return userMapper.update(state,staffid);
    }

    public int updatepassword(String password,String staffid) {
        return userMapper.updatepassword(password,staffid);
    }
}