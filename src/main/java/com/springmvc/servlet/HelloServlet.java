package com.springmvc.servlet;

import com.springmvc.bean.Person;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/11/12 16:53
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //访问到SpringIOC容器中的person对象.
        //从ServletContext对象中获取SpringIOC容器对象
        ServletContext servletContext = getServletContext();
        ApplicationContext context = (ApplicationContext)servletContext.getAttribute("applicationContext");
        Person person = context.getBean("person", Person.class);
        person.sayHello();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
