<%--
  Created by IntelliJ IDEA.
  User: FYkf
  Date: 2019/10/29
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="false" %>
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

</body>
</html>
