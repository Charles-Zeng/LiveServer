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
    <!-- Custom styles for register -->
    <link href="/static/css/register.css" rel="stylesheet">
</head>
<body>

  <div class="container">
      <form class="form-horizontal" action="/register" onsubmit="return validate_form(this)" method="post">

          <!-- 用户名 -->
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">用户名</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="username" name="username">
              </div>
          </div>

          <!-- 密码 -->
          <div class="form-group">
              <label for="password" class="col-sm-2 control-label">密 码</label>
              <div class="col-sm-10">
                  <input type="password" class="form-control" id="password" name="password">
              </div>
          </div>

          <!-- 确认密码 -->
          <div class="form-group">
              <label for="confirmPwd" class="col-sm-2 control-label">确认密码</label>
              <div class="col-sm-10">
                  <input type="password" class="form-control" id="confirmPwd" name="confirmPwd">
              </div>
          </div>

          <!-- 电话 -->
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">电 话</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="tel" name="tel">
              </div>
          </div>

          <!-- 姓名 -->
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">姓 名</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="name" name="name">
              </div>
          </div>

          <!-- 地址 -->
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">地 址</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="address" name="address">
              </div>
          </div>

          <!-- 身份证号码 -->
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">身份证号码</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="idCardNum" name="idCardNum">
              </div>
          </div>

          <!-- 推流地址 -->
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">推流地址</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="pushAddress" name="pushAddress">
              </div>
          </div>

          <!-- 自动停止推流分钟数 -->
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">自动停止推流分钟数</label>
              <div class="col-sm-10">
                  <input type="number" class="form-control" id="autoStopPushMinutes" name="autoStopPushMinutes">
              </div>
          </div>

          <!-- 是否是管理员 -->
          <!--div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                  <div class="checkbox">
                      <label>
                          <input type="checkbox" name="isAdmin"> 管理员
                      </label>
                  </div>
              </div>
          </div-->

          <!-- 注册按钮 -->
          <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-default">注 册</button>
              </div>
          </div>

      </form>
  </div>

  <script src="/static/jquery/jquery-3.2.1.min.js"></script>
  <script src="/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<script>
    function validate_required(field,alertText)
    {
        if (field.value == null || field.value == ""){
            alert(alertText);
            return false;
        }

        return true;
    }

    function validate_form(thisForm)
    {
        with (thisForm)
        {
            if (validate_required(username, "用户名不能为空") == false) {
                username.focus();
                return false;
            }

            if (validate_required(password, "密码不能为空") == false) {
                password.focus();
                return false;
            }

            if (validate_required(confirmPwd, "确认密码不能为空") == false) {
                confirmPwd.focus();
                return false;
            }

            if (validate_required(tel, "电话不能为空") == false) {
                tel.focus();
                return false;
            }

            if (validate_required(name, "姓名不能为空") == false) {
                name.focus();
                return false;
            }

            if (isCharacterOrChinese(name.value) == false) {
                alert("姓名只能是中文或英文");
                name.focus();
                return false;
            }

            if (validate_required(address, "地址不能为空") == false) {
                address.focus();
                return false;
            }

            if (isNumbers(address.value) == true) {
                alert("地址不能是全数字");
                address.focus();
                return false;
            }

            if (validate_required(idCardNum, "身份证不能为空") == false) {
                idCardNum.focus();
                return false;
            }

            if (validate_required(pushAddress, "推流地址不能为空") == false) {
                pushAddress.focus();
                return false;
            }

            if (validate_required(autoStopPushMinutes, "自动停止推流分钟数不能为空") == false) {
                autoStopPushMinutes.focus();
                return false;
            }

            return true;
        }
    }
    
    function isCharacterOrChinese(s) {
        var regu = "^[a-zA-Z\u4e00-\u9fa5]+$";
        var re = new RegExp(regu);
        if (re.test(s)) {
            return true;
        }

        return false;
    }

    function isNumbers(s) {
        var regu = "^[0-9]+$";
        var re = new RegExp(regu);
        if (re.test(s)) {
            return true;
        }

        return false;
    }
</script>

</body>
</html>
