<%--
  Created by IntelliJ IDEA.
  User: CharlesZeng
  Date: 2018-02-08
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.live.model.UserInfo" %>

<html>
<head>
    <title>历史记录管理界面</title>
    <link href="/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="background-color:steelblue;font-size: large">
    <div class="container-fluid">
        <div>
            <ul class="nav navbar-nav navbar-left">
                <li><a href="/userManager">用户管理</a></li>
                <li><a href="/deviceManager">设备管理</a> </li>
                <li class="active"><a href="/deviceHistoryManager">历史记录</a> </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <button type="button" class="btn btn-default navbar-btn" onclick="doPost('/logout', {})">登出</button>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <form class="form-inline" action="/deviceHistoryManager" method="post">
        <%
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            if (userInfo.getIsAdmin() == 1){
         %>
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>
        <%
            }
        %>

        <div class="form-group">
            <label for="serviceName">服务名</label>
            <input type="text" class="form-control" id="serviceName" name="serviceName">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
    </form>
</div>

<div class="container">
    <table class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
            <th>用户名</th>
            <th>IP</th>
            <th>MAC</th>
            <th>IMEI</th>
            <th>GPS</th>
            <th>服务名称</th>
            <th>登陆时间</th>
            <th>登出时间</th>
            <th>在线时长</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${deviceHistories}" var="deviceHistory">
            <tr>
                <td><c:out value="${deviceHistory.username}" /></td>
                <td><c:out value="${deviceHistory.ip}" /></td>
                <td><c:out value="${deviceHistory.mac}" /></td>
                <td><c:out value="${deviceHistory.imei}" /></td>
                <td><c:out value="${deviceHistory.gps}" /></td>
                <td><c:out value="${deviceHistory.serviceName}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd  HH:mm:ss" value="${deviceHistory.loginTime}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd  HH:mm:ss" value="${deviceHistory.logoutTime}" /></td>
                <td><c:out value="${deviceHistory.durationTime}s" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="/static/jquery/jquery-3.2.1.min.js"></script>
<script src="/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<!--script src="/static/js/common.js"></script-->

<script>
    function doPost (url,args)
    {
        console.log(url, args);
        var myForm = document.createElement("form");
        myForm.method = "post";
        myForm.action = url;
        for ( var k in args) {
            var myInput = document.createElement("input");
            myInput.setAttribute("name", k);
            myInput.setAttribute("value", args[k]);
            myForm.appendChild(myInput);
        }
        document.body.appendChild(myForm);
        myForm.submit();
        document.body.removeChild(myForm);
    }
</script>

</body>
</html>
