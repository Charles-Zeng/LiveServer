package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import com.live.model.*;
import com.live.dao.*;

public class Register extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("注册界面");

        UserInfoDao dao = new UserInfoDao();
        UserInfo userQuery = dao.getUserInfoByUsername(req.getParameter("username"));
        if (userQuery != null ){
            req.setAttribute("usernameTips", "用户已存在");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        if(!req.getParameter("password").equals(req.getParameter("confirmPwd"))){
            req.setAttribute("pwdTips", "两次输入密码不一致");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(req.getParameter("username"));
        userInfo.setPassword(req.getParameter("password"));
        userInfo.setTel(req.getParameter("tel"));
        userInfo.setName(req.getParameter("name"));
        userInfo.setAddress(req.getParameter("address"));
        userInfo.setIdCardNum(req.getParameter("idCardNum"));
        userInfo.setPushAddress(req.getParameter("pushAddress"));
        userInfo.setAutoStopPushMinutes(Integer.valueOf(req.getParameter("autoStopPushMinutes")));


        dao.addUserInfo(userInfo);

        resp.sendRedirect("/login.jsp");
    }
}
