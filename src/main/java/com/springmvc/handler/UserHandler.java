package com.springmvc.handler;

import org.springframework.stereotype.Controller;

/**
 * @author Lee
 * @create 2019/11/12 17:52
 */
@Controller
public class UserHandler {

    public UserHandler() {
        System.out.println(this.getClass().getName());
    }
}
