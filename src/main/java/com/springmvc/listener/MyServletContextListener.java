package com.springmvc.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Lee
 * @create 2019/11/12 16:54
 */
public class MyServletContextListener implements ServletContextListener {
    /**
     * 当监听到ServletContext被创建，则执行该方法
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1. 创建SpringIOC容器对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //2. 将SpringIOC容器对象绑定到ServletContext中
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("applicationContext",context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
