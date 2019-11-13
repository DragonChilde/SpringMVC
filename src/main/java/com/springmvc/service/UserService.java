package com.springmvc.service;

import com.springmvc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lee
 * @create 2019/11/13 15:03
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserService() {
        System.out.println(this.getClass().getName() + "....");
    }

    public void hello()
    {
        userDao.hello();
    }
}
