package com.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lee
 * @create 2019/10/29 10:33
 */
@Controller
//@RequestMapping("springmvc")
public class SpringmvcHandler {

    /**
     * @RequestMapping
     */
    @RequestMapping("testRequestMapping")
    public String testRequestMapping()
    {
        return "success";
    }


    /**
     * @RequestMapping  method 映射请求方式
     *
     */
    @RequestMapping(value = "testRequestMappingMethod",method = {RequestMethod.GET})
    public String testRequestMappingMethod(){
        return "success";
    }

    /**
     * @RequestMapping  映射请求参数   以及  请求头信息
     *
     * params  : username=tom&age=22
     * headers
     */
    @RequestMapping(value = "testRequestMappingParamsAndHeaders",params = {"username!=test","age"},headers = {"!Accept-Language"})
    public String testRequestMappingParamsAndHeaders(){
        return "success";
    }

    /**
     * 带占位符的URL
     *
     * 浏览器: http://localhost:8080/SpringMVC/testPathVariable/admin/1001
     */
    @RequestMapping(value = "testPathVariable/{name}/{id}")
    public String testPathVariable(@PathVariable("name") String name,@PathVariable("id")Integer id)
    {
        System.out.println("name is "+name);
        System.out.println("id is "+id);
        return "success";
    }


    /**
     * REST GET
     */
    @RequestMapping(value = "order/{id}",method = {RequestMethod.GET})
    public String testRestGet(@PathVariable("id") Integer id){
        System.out.println("REST GET "+ id);
        return "success";
    }
    /**
     * REST DELETE
     */
    @RequestMapping(value = "order/{id}",method = {RequestMethod.DELETE})
    public String testRestDelete(@PathVariable("id") Integer id){
        System.out.println("REST DELETE "+ id);
        return "success";
    }

    /**
     * REST POST
     */
    @RequestMapping(value = "order",method = {RequestMethod.POST})
    public String testRestPost(){
        System.out.println("REST POST ");
        return "success";
    }
    /**
     * REST PUT
     */
    @RequestMapping(value = "order",method = {RequestMethod.PUT})
    public String testRestPut(){
        System.out.println("REST PUT ");
        return "success";
    }
}
