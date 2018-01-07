<%--
  Created by IntelliJ IDEA.
  User: works-new
  Date: 2018/1/4
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% String message = (String)(request.getAttribute("message")); %>

<html>
<head>
    <title>登陆界面</title>
    <link href="/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div id="login" align="center">
    <form id="id_login" action="/login" method="post">
        <table border="1" style="border-collapse:collapse;">
            <tr align = "center">
                <td colspan="2">用户登录</td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>
            <tr>
                <td>密  码：</td>
                <td>
                    <input type="password" name="password">
                    <span style = "color:red; font-size:13px;" id = "errorMsg"><% if(message != null) out.print(message); %></span>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="login" value="登录">
                    <input type="button" name="register" value="注册" onclick="window.location.href = '/register.jsp'">
                </td>
            </tr>
        </table>
    </form>
</div>

<script src="/static/jquery/jquery-3.2.1.min.js"></script>
<script src="/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
