package com.springmvc.handler.modelandview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Lee
 * @create 2019/10/30 15:21
 */
@Controller
public class SpringmvcModelAndViewHandler {

    /**
     * ModelAndView
     * 结论: Springmvc会把ModelAndView中的模型数据存放到request域对象中.
     */
    @RequestMapping(value = "testModelAndView")
    public ModelAndView testModelAndView()
    {
        //模型数据: username=Admin
        ModelAndView mav = new ModelAndView();
        //添加模型数据
        mav.addObject("username","admin");
        //设置视图信息
        mav.setViewName("view");
        return mav;
    }

    /**
     * Map
     * 结论: SpringMVC会把Map中的模型数据存放到request域对象中.
     *      SpringMVC再调用完请求处理方法后，不管方法的返回值是什么类型，都会处理成一个ModelAndView对象（参考DispatcherServlet的945行）
     *
     */
    @RequestMapping(value = "testMap")
    public String testMap(Map<String,Object> map)
    {
        System.out.println(map.getClass().getName());   //org.springframework.validation.support.BindingAwareModelMap

        map.put("password" ,123456);
        return "view";
    }

    @RequestMapping(value = "testModel")
    public String testModel(Model model)
    {
        model.addAttribute("loginMsg","用户名或者密码错误！");
        return "view";
    }
}
