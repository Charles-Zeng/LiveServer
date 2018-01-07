<%--
  Created by IntelliJ IDEA.
  User: CharlesZeng
  Date: 2018-01-06
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>管理界面</title>
    <link href="/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div>
            <ul class="nav navbar-nav navbar-left">
                <li class="active"><a href="/userManager">用户管理</a></li>
                <li><a href="/deviceManager">设备管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">登出</a> </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <table border=1>
        <thead>
        <tr>
            <th>用户名</th>
            <th>电话</th>
            <th>姓名</th>
            <th>地址</th>
            <th>身份证号码</th>
            <th>推流地址</th>
            <th>自动停止推流分钟数</th>
            <th colspan=2>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.username}" /></td>
                <td><c:out value="${user.tel}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.address}" /></td>
                <td><c:out value="${user.idCardNum}" /></td>
                <td><c:out value="${user.pushAddress}" /></td>
                <td><c:out value="${user.autoStopPushMinutes}" /></td>
                <td><a href="/userManager?action=edit&userId=<c:out value="${user.username}"/>">Update</a></td>
                <td><a href="/userManager?action=delete&userId=<c:out value="${user.username}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="/static/jquery/jquery-3.2.1.min.js"></script>
<script src="/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
