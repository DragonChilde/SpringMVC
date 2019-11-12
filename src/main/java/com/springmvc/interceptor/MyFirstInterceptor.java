package com.springmvc.interceptor;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lee
 * @create 2019/11/8 17:29
 */
/**
 * 自定义拦截器
 */
/*@Component*/
public class MyFirstInterceptor implements HandlerInterceptor {

    /**
     * 1. 是在DispatcherServlet的962行   在请求处理方法之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println(this.getClass().getName() + " preHandle");
        return true;
    }

    /**
     * 2. 在DispatcherServlet 974行   请求处理方法之后，视图处理之前执行。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println(this.getClass().getName() + " postHandle");
    }

    /**
     * 3.
     * 	 [1].在DispatcherServlet的 1059行   视图处理之后执行.(转发/重定向后执行)
     * 	 [2].当某个拦截器的preHandle返回false后，也会执行当前拦截器之前拦截器的afterCompletion
     *   [3].当DispatcherServlet的doDispatch方法抛出异常,也可能会执行拦截器的afterCompletion
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println(this.getClass().getName() + " afterCompletion");
    }
}
