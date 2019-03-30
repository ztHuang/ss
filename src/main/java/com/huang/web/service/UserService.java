package com.huang.web.service;

import com.huang.web.dao.UserDao;
import com.huang.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.03.28
 * @Version 1.0
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public boolean tx() {
        User u = new User(2,"tom");
        userDao.insert(u);
        return true;
    }
}
