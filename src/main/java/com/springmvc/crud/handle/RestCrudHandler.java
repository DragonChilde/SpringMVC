package com.springmvc.crud.handle;

import com.springmvc.crud.beans.Department;
import com.springmvc.crud.beans.Employee;
import com.springmvc.crud.dao.DepartmentDao;
import com.springmvc.crud.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.ExportException;
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

    /**
     * 修改功能: 去往修改页面
     */
    @RequestMapping(value = "emp/{id}",method = RequestMethod.GET)
    public String toUpdatePage(@PathVariable("id")Integer id,Map<String,Object> map)
    {
        //查询要修改的员工信息
        Employee employee = employeeDao.get(id);
        map.put("employee",employee);

        Collection<Department> departments = departmentDao.getDepartments();
        map.put("depts",departments);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("0","女");
        hashMap.put("1","男");
        map.put("genders",hashMap);

        return "input";
    }

    /**
     * 修改功能: 具体的修改操作
     */
    @RequestMapping(value = "emp",method = RequestMethod.PUT)
    public String editEmp(Employee employee)
    {
        employeeDao.save(employee);
        return "redirect:/emps";
    }


    /**
     处理Json
     **/
    @ResponseBody   // 负责将方法的返回值 转化成json字符串 响应给浏览器端.
    @RequestMapping(value = "testJson")
    public Collection<Employee> testJson()
    {
        Collection<Employee> emps = employeeDao.getAll();
        System.out.println(emps);
        return emps;
    }


    /**
     * 使用HttpMessageConveter完成下载功能:
     *
     * 支持  @RequestBody   @ResponseBody   HttpEntity  ResponseEntity
     *
     * 下载的原理:  将服务器端的文件 以流的形式  写到 客户端.
     * ResponseEntity: 将要下载的文件数据， 以及响应信息封装到ResponseEntity对象中，浏览器端通过解析
     * 				       发送回去的响应数据， 就可以进行一个下载操作.
     */
    @RequestMapping(value = "download")
    public ResponseEntity<byte[]> testDownload(HttpSession session) throws IOException
    {
        //一,定义一个空的字符数组
        byte[] bytes;

        //二,获取本地图片的流
        ServletContext context = session.getServletContext();
        InputStream stream = context.getResourceAsStream("image/test.jpg");

        //三,定义长度大小的字符
        //available()在非阻塞情况下(本地读取,非网络流),获取已经流的长度大小
        bytes = new byte[stream.available()];

        //四,把本地流写入字符数组里
        stream.read(bytes);

        //五定义一个HTTP头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=test.jpg");

        //返回ResponseEntity
        ResponseEntity<byte[]> entity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);

        return entity;

    }

    /**
     * 文件的上传
     * 上传的原理:  将本地的文件 上传到 服务器端
     */
/*    @RequestMapping(value = "upload" )
    public String testUploadFile(@RequestParam("desc") String desc,@RequestParam("uploadFile")MultipartFile multipartFile, HttpSession session) throws IOException
    {

        //获取到上传文件的名字
        String filename = multipartFile.getOriginalFilename();
        //获取输入流
        InputStream in = multipartFile.getInputStream();

        //获取服务器端的uploads文件夹的真实路径。
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("uploads");

        File file = new File(realPath + "/" + filename);

        FileOutputStream out = new FileOutputStream(file);

        //写文件
        int i;
        while ((i = in.read()) != -1)
        {
            out.write(i);
        }
        out.close();
        in.close();

        return "uploadsuccess";
    }*/


    /*上传文件更简单的实现,调用MultipartFile类里封装好的transferTo()方法,原理也是用Stream流进行读写*/
    @RequestMapping(value = "upload" )
    public String testUploadFile(@RequestParam("desc") String desc,@RequestParam("uploadFile")MultipartFile multipartFile, HttpSession session) throws IOException
    {
        String filename = multipartFile.getOriginalFilename();

        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("uploads");
        File file = new File(realPath + "/" + filename);
        multipartFile.transferTo(file);
        return "uploadsuccess";
    }



    @RequestMapping(value = "interceptor")
    public String testInterceptor()
    {
        return "success";
    }


}
