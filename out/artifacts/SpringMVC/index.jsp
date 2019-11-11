<%--
  Created by IntelliJ IDEA.
  User: FYkf
  Date: 2019/10/29
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>index</title>
</head>


<body>
<a href="hello">SpringMvc Hello World Handler</a>
<br/>
<a href="testRequestMapping">Test Request Mapping</a>
<br/>
<a href="testRequestMappingMethod">Test RequestMapping Method</a>
<br/>
<form action="testRequestMappingMethod" method="post">
    <input type="submit" value="POST"/>
</form>
<br/>
<a href="testRequestMappingParamsAndHeaders?username=test&age=20">Test RequestMapping Params  Headers</a>
<br/>
<a href="testPathVariable/admin/1001">Test PathVaribale</a>
<br/>
<!-- 修改一个订单 -->
<form action="order" method="post">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="submit" value="REST PUT"/>
</form>
<br/>

<!-- 添加一个新的订单 -->
<form action="order" method="post">
    <input type="submit" value="REST POST"/>
</form>
<br/>
<!-- 删除id为1001的订单 -->
<form action="order/1001" method="post">
    <!-- 隐藏域· -->
    <input type="hidden" name="_method" value="DELETE"/>
    <input type="submit" value="REST DELETE"/>
</form>
<br/>
<!-- 查询id为1001的订单 -->
<a href="order/1001">REST GET</a>
<br/>
<a href="testRequestParam?username=test">Test Request Param</a>
<br>
<a href="testRequestHeader">Test Request Header</a>
<br/>
<a href="testCookieValue">Test Cookie Value</a>
<br/>
<form action="testPOJO" method="post">
    用户名称: <input type="text" name="username"/>
    <br/>
    用户密码: <input type="password" name="password"/>
    <br/>
    用户邮箱: <input type="text" name="email"/>
    <br/>
    用户性别: 男 <input type="radio" name="gender" value="1"/>
    女<input type="radio" name="gender" value="0"/>
    <br/>
    <!-- 支持级联的方式 -->
    用户省份: <input type="text" name="address.province" />
    <br/>
    用户城市: <input type="text" name="address.city"/>
    <br/>
    <input type="submit" value="注册"/>

</form>
<br/>
<a href="testServletAPI">Test Servlet API</a>
<br/>
<a href="testModelAndView">Test Model And View</a>
<br/>
<a href="testMap">Test Map</a>
<br/>
<a href="testViewContorller">Test ViewController</a>
<br/>
<a href="testRedirectView">Test RedirectView</a>
<br/>
<a href="emps">List All Emps</a>
<br/>
<a href="testJson">Test Json</a>
<br/>
<a href="download">Test Download</a>
<br/>

<form action="upload" method="post" enctype="multipart/form-data">
    上传文件:<input type="file" name="uploadFile"/>
    <br/>
    文件描述:<input type="text" name="desc"/>
    <br/>
    <input type="submit" value="上传"/>
</form>

<br/>
<a href="interceptor">Test Interceptor</a>
</body>
</html>
