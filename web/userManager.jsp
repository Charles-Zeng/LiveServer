<%--
  Created by IntelliJ IDEA.
  User: CharlesZeng
  Date: 2018-01-06
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="com.live.model.UserInfo" %>

<html>
<head>
    <title>管理界面</title>
    <link href="static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="background-color:steelblue;font-size: large">
    <div class="container-fluid">
        <div>
            <ul class="nav navbar-nav navbar-left">
                <li class="active"><a href="/userManager">用户管理</a></li>
                <li><a href="/deviceManager">设备管理</a></li>
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
    <table class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
            <th>用户名</th>
            <th>电话</th>
            <th>姓名</th>
            <th>地址</th>
            <th>身份证号码</th>
            <th>推流地址</th>
            <th>自动停止推流分钟数</th>
            <th>用户状态</th>
            <%
                UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
                if (userInfo.getIsAdmin() == 1){
                    out.println("<th colspan=3>操作</th>");
                }
            %>
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
                <td>
                    <c:choose>
                        <c:when test="${user.userStatus == 0}">禁用</c:when>
                        <c:otherwise>可用</c:otherwise>
                    </c:choose>
                </td>
                <!-- 管理员操作 -->
                <% if (userInfo.getIsAdmin() == 1){ %>
                    <td>
                        <c:choose>
                            <c:when test="${user.userStatus == 0}">
                                <button onclick="doPost('/userManager', {'action':'onUser', 'username':'${user.username}'})">启用</button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="doPost('/userManager', {'action':'offUser', 'username':'${user.username}'})">禁用</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><button onclick="c()">修改</button></td>
                    <td>
                        <button onclick="doPost('/userManager', {'action':'deleteUser', 'username':'${user.username}'})">删除</button>
                    </td>
                <% } %>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="static/jquery/jquery-3.2.1.min.js"></script>
<script src="static/bootstrap-3.3.7/js/bootstrap.min.js"></script>

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

    function c(){
        alert('单击了我');
    }
</script>

</body>
</html>
