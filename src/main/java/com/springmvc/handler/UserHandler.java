package com.springmvc.handler;

import com.springmvc.bean.Person;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @author Lee
 * @create 2019/11/12 17:52
 */
@Controller
public class UserHandler {

    @Autowired
    private UserService userService;

    public UserHandler() {
        System.out.println(this.getClass().getName()+"....");
    }

    /*在方法里添加Servlet session*/
    @RequestMapping("hellospring")
    public String hello(HttpSession session)
    {
       userService.hello();

       /*从Spring ioc容器中获取容器对象方法*/
        ServletContext context = session.getServletContext();
        /*方法一,这种方法比较旧,要记很长的key,可以使用下面springmvc提供的工具类,使用的方式都是一样,只是springmvc封装好了*/
       /* ApplicationContext applicationContext = (ApplicationContext)context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);*/
        /*方法二*/
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        Person person = applicationContext.getBean("person", Person.class);
        person.sayHello();

        return "success";
    }

}
