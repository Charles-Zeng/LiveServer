<%--
  Created by IntelliJ IDEA.
  User: CharlesZeng
  Date: 2018-01-30
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% String message = (String)(request.getAttribute("message")); %>

<html>
<head>
    <meta charset="utf-8">
    <title>管理员登陆界面</title>
    <link href="/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for login -->
    <link href="/static/css/signin.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <form class="form-signin" action="adminLogin" method="post">
        <h2 class="form-signin-heading">管理员登陆</h2>
        <label for="username" class="sr-only">管理员用户名</label>
        <input type="text" name="username" id="username" class="form-control" placeholder="管理员用户名" required autofocus>
        <label for="password" class="sr-only">管理员密码</label>
        <input type="password" name="password" id="password" class="form-control" placeholder="管理员密码" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登 陆</button>
        <h4 style = "color:red;" id = "errorMsg"><% if(message != null) out.print(message); %></h4>
    </form>

</div> <!-- /container -->

<script src="/static/jquery/jquery-3.2.1.min.js"></script>
<script src="/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
