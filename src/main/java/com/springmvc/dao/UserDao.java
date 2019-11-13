package com.springmvc.dao;

import org.springframework.stereotype.Component;

/**
 * @author Lee
 * @create 2019/11/13 15:01
 */
@Component
public class UserDao {

    public UserDao() {
        System.out.println(this.getClass().getName() + ".....");
    }

    public void hello()
    {
        System.out.println(this.getClass().getName() + " hello");
    }
}
