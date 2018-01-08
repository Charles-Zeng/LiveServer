<%--
  Created by IntelliJ IDEA.
  User: works-new
  Date: 2018/1/4
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% String pwdTips = (String)(request.getAttribute("pwdTips")); %>
<% String usernameTips = (String)(request.getAttribute("usernameTips")); %>

<html>
<head>
    <title>注册界面</title>
    <link href="/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <div id="register" align="center">
      <form action="/register" method="post">
          <table border="1" style="border-collapse:collapse;">
              <tr>
                  <td>用户名：</td>
                  <td>
                      <input type="text" name="username">
                      <span style = "color:red; font-size:13px;" id = "usernameTips"><% if(usernameTips != null) out.print(usernameTips); %></span>
                  </td>
              </tr>
              <tr>
                  <td>密  码：</td> <td><input type="password" name="password"></td>
              </tr>
              <tr>
                  <td>确认密码：</td>
                  <td>
                      <input type="password" name="confirmPwd">
                      <span style = "color:red; font-size:13px;" id = "pwdTips"><% if(pwdTips != null) out.print(pwdTips); %></span>
                  </td>
              </tr>
              <tr>
                  <td>电  话：</td> <td><input type="text" name="tel"></td>
              </tr>
              <tr>
                  <td>姓  名：</td> <td><input type="text" name="name"></td>
              </tr>
              <tr>
                  <td>地  址：</td> <td><input type="text" name="address"></td>
              </tr>
              <tr>
                  <td>身份证号码：</td> <td><input type="text" name="idCardNum"></td>
              </tr>
              <tr>
                  <td>推流地址：</td> <td><input type="text" name="pushAddress"></td>
              </tr>
              <tr>
                  <td>自动停止推流分钟数：</td> <td><input type="number" name="autoStopPushMinutes"></td>
              </tr>
              <tr>
                  <td colspan="2" align="right"><input type="submit" value="注册"></td>
              </tr>
          </table>
      </form>
  </div>

  <script src="/static/jquery/jquery-3.2.1.min.js"></script>
  <script src="/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
