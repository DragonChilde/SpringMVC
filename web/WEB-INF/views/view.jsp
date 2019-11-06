<%--
  Created by IntelliJ IDEA.
  User: FYkf
  Date: 2019/10/30
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>
username: ${requestScope.get("username")}    <!-- 四个域对象: pageScope  requestScope sessionScope  applicationScope -->
<br/>
password: ${requestScope.get("password")}
<br/>

</body>
</html>
