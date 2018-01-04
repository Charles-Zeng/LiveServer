<%--
  Created by IntelliJ IDEA.
  User: works-new
  Date: 2018/1/4
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
  <form action="/register" method="post">
      用户名：<input type="text" name="username"> <br>
      密  码：<input type="text" name="password"> <br>
      确认密码：<input type="password" name="confirmPwd"> <br>
      <input type="submit" value="注册">
  </form>
</body>
</html>
