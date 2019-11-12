package com.springmvc.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lee
 * @create 2019/10/28 11:42
 */
@Controller
public class SpringMVCHandler {

    /**
     * 处理客户端的请求:  http://localhost:8888/springmvc/hello
     *
     * @RequestMapping: 完成请求 与 请求处理方法的映射.
     *
     */
    @RequestMapping(value = "hello")
    public String handleHello()
    {
        System.out.println("hello world!");
        return "success";
    }


    @RequestMapping(value = "helloservlet")
    public String helloServlet()
    {
        return "success";
    }
}
