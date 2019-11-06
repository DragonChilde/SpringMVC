package com.springmvc.crud.handle;

import com.springmvc.crud.beans.Department;
import com.springmvc.crud.beans.Employee;
import com.springmvc.crud.dao.DepartmentDao;
import com.springmvc.crud.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 显示所有的员工信息列表
     */
    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    public String listAllEmps(Map<String,Object> map)
    {
        Collection<Employee> emps = employeeDao.getAll();
        map.put("emps",emps);
        return "list";
    }

    /**
     * 添加功能: 去往添加页面
     *
     * 添加页面需要部门数据
     */
    @RequestMapping(value = "emp",method = RequestMethod.GET)
    public String toAddPage(Map<String,Object>map)
    {

        Collection<Department> departments = departmentDao.getDepartments();
        map.put("depts",departments);

        //2. 构造页面中生成单选框的数据
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("0","女");
        hashMap.put("1","男");

        //3. 设置页面中要回显的数据
        //解决错误：java.lang.IllegalStateException: Neither BindingResult nor plain target object for bean name 'command' available as request attribute
        map.put("employee",new Employee());

        map.put("genders",hashMap);
        return "input";
    }


    /**
     * 添加功能 : 具体的添加操作
     */
    @RequestMapping(value = "emp" ,method = RequestMethod.POST)
    public String addEmp(Employee employee)
    {
        //添加员工
        employeeDao.save(employee);
        //回到列表页面 :重定向到显示所有员工信息列表的请求.
        return "redirect:/emps";
    }



    /**
     * 删除功能
     */
    @RequestMapping(value = "emp/{id}",method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id")Integer id)
    {
        //删除员工
        employeeDao.delete(id);
        //重定向到列表
        return "redirect:/emps";
    }
}
