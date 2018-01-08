package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import com.live.dao.*;

public class UserManager extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("用户管理界面");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        UserInfoDao dao = new UserInfoDao();

        req.setAttribute("users", dao.getAllUserInfo());
        req.getRequestDispatcher("/userManager.jsp").forward(req, resp);
    }
}
