package com.springmvc.handler;

import com.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    /**
     * @RequestParam  映射请求参数到请求处理方法的形参
     * 	 1. 如果请求参数名与形参名一致， 则可以省略@RequestParam的指定。
     * 	 2. @RequestParam 注解标注的形参必须要赋值。 必须要能从请求对象中获取到对应的请求参数。
     * 		可以使用required来设置为不是必须的。
     * 	 3. 可以使用defaultValue来指定一个默认值取代null
     * 客户端的请求:testRequestParam?username=Tom&age=22
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam(value = "username") String username ,@RequestParam(value = "age",required = false,defaultValue = "20") int age)
    {

        System.out.println("username is "+username+" , age is "+age);
        return "success";
    }

    /**
     * @RequestHeader  映射请求头信息到请求处理方法的形参中
     */
    @RequestMapping(value = "testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String acceptLanguage)
    {
        System.out.println("Accept-Language is "+acceptLanguage);
        return "success";
    }

    /**
     * @CookieValue  映射cookie信息到请求处理方法的形参中
     */
    @RequestMapping(value = "testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID")String sessionid)
    {
        System.out.println("JSESSIONID is " +sessionid);
        return "success";
    }

    /**
     * POJO
     */
    @RequestMapping(value = "testPOJO")
    public String testPOJO(User user)
    {
        System.out.println("User is "+user);
        return "success";
    }

    @RequestMapping(value = "testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 转发
        //request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request,response);

        //重定向
        //response.sendRedirect("http://www.baidu.com");

        response.getWriter().println("Hello SpringMVC!");
    }
}
