<%--
  Created by IntelliJ IDEA.
  User: CharlesZeng
  Date: 2018-01-06
  Time: 12:02
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
<nav class="navbar navbar-default" role="navigation" style="background-color:steelblue;font-size: large">
    <div class="container-fluid">
        <div>
            <ul class="nav navbar-nav navbar-left">
                <li><a href="/userManager">用户管理</a></li>
                <li class="active"><a href="/deviceManager">设备管理</a> </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">登出</a> </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <table class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
            <th>IP</th>
            <th>MAC</th>
            <th>IMEI</th>
            <th>GPS</th>
            <th>服务名称</th>
            <th>用户名</th>
            <th>采集状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${devices}" var="device">
            <tr>
                <td><c:out value="${device.ip}" /></td>
                <td><c:out value="${device.mac}" /></td>
                <td><c:out value="${device.imei}" /></td>
                <td><c:out value="${device.gps}" /></td>
                <td><c:out value="${device.serviceName}" /></td>
                <td><c:out value="${device.username}" /></td>
                <td>
                    <c:if test="${device.status == 0}">
                        <c:out value="关闭"></c:out>
                    </c:if>
                    <c:if test="${device.status == 1}">
                        <c:out value="开启"></c:out>
                    </c:if>
                </td>
                <td>
                    <c:if test="${device.status == 0}">
                        <!-- a href="/deviceManager?action=switchOn&ip=<c:out value="${device.ip}"/>">开启采集</--a -->
                        <button onclick="doPost('/deviceManager', {'action':'switchOn', 'ip':'${device.ip}'})">开始采集</button>
                    </c:if>
                    <c:if test="${device.status == 1}">
                        <!--a href="/deviceManager?action=switchOff&ip=<c:out value="${device.ip}"/>">关闭采集</a -->
                        <button onclick="doPost('/deviceManager', {'action':'switchOff', 'ip':'${device.ip}'})">关闭采集</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="/static/jquery/jquery-3.2.1.min.js"></script>
<script src="/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<script>
    function doPost (url,args)
    {
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
