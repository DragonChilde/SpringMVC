package com.springmvc.crud.handle;

import com.springmvc.crud.beans.Employee;
import com.springmvc.crud.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * @author Lee
 * @create 2019/11/5 14:46
 */
@Controller
public class RestCrudHandler {
    
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    public String listAllEmps(Map<String,Object> map)
    {
        Collection<Employee> emps = employeeDao.getAll();
        map.put("emps",emps);
        return "list";
    }
}
