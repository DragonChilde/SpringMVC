<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/5
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<!-- 导入JSTL的标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 引入JQuery
Springmvc处理静态资源的问题:
静态资源:  .js  .css  .html  .txt  .png  .jpg  .avi 等.

因为DispatcherServlet的 <url-pattern> 配置的是/ , 会匹配到所有的请求(排除jsp的请求).
因为请求的.js文件，是一个静态资源请求，交给DispatcherServlet后就会出现no mapping found 问题。
解决问题:
1. 修改<url-pattern>为后缀匹配. 但是不建议这么做， 对REST的支持不好. 因为一个优秀的REST 不希望请求
URL带有任何后缀.
2. 在springmvc.xml中加上一个配置: <mvc:default-servlet-handler/>
<mvc:annotation-driven/>

-->
<script type="text/javascript" src="scripts/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    $(function(){

        //给删除的<a>动态绑定事件
        $(".del").click(function(){
            //确认是否要删除
            var flag = window.confirm("是否要删除?");
            if(!flag){
                return false ;
            }

            //this :当前点击的dom对象
            //获取点击的超连接的href的值
            var href = $(this).attr("href");
            //将href的值设置到表单的action上，并提交
            $("form").attr("action",href).submit();

            //取消<a>的默认行为
            return false;

        });
    });


</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">员工信息列表</h1>
<form action="/emp" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
</form>
<table border="1px" align="center" width="70%" cellspacing="0px">
    <tr>
        <th>Id</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Gender</th>
        <th>DeptName</th>
        <th>Operation</th>
    </tr>

    <!-- 通过迭代模型数据，生成表格 -->
    <c:forEach items="${emps }" var="emp">
        <tr align="center">
            <td>${emp.id }</td>
            <td>${emp.lastName }</td>
            <td>${emp.email }</td>
            <td>${emp.gender==0?"女":"男"}</td>
            <td>${emp.department.departmentName }</td>
            <td>
                <a href="emp/${emp.id}">Edit </a>
                &nbsp;&nbsp;
                <!--
                    解决思路:
                        给删除的超链接绑定事件， 当触发了点击事件，可以在事件处理函数中获取到要发送的请求URL,
                        再将获取到的请求URL设置到某个表单的action属性上，再将表单提交。
                        最终将<a>的默认行为取消掉。

                 -->
                <a  class="del" href="emp/${emp.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<h2 align="center"><a href="emp">Add New Emp</a></h2>
</body>
</html>
