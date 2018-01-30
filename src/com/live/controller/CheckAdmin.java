package com.live.controller;

import com.live.dao.UserInfoDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CheckAdmin extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserInfoDao dao = new UserInfoDao();

        if (dao.isExistAdmin()) {
            resp.sendRedirect("/adminLogin.jsp");
            return;
        }

        resp.sendRedirect("/adminRegister.jsp");
    }
}
