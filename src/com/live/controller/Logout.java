package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        session.invalidate();
        resp.sendRedirect("/login.jsp");
        return;
    }
}
