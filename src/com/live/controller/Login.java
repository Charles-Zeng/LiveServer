package com.live.controller;

import com.live.dao.UserInfoDao;
import com.live.model.UserInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("登录界面");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        if (userName.isEmpty() || password.isEmpty())
        {
            req.setAttribute("message", "用户名密码不能为空");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        UserInfoDao dao = new UserInfoDao();
        UserInfo userInfo = dao.getUserInfoByUsername(userName);
        if (userInfo == null || !userInfo.getPassword().equals(password))
        {
            req.setAttribute("message", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/userManager");
    }
}
