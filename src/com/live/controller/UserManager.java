package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.live.dao.*;
import com.live.model.UserInfo;

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

        // user login status check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userInfo") == null){
            resp.sendRedirect("/login.jsp");
            return;
        }

        String action = req.getParameter("action");
        String username = req.getParameter("username");
        if (action != null && username != null){
            if (action.equals("onUser")){
                processOnUser(username);
            }

            if (action.equals("offUser")){
                processOffUser(username);
            }

            if (action.equals("deleteUser")){
                processDelete(username);
            }
        }

        UserInfoDao dao = new UserInfoDao();

        req.setAttribute("users", dao.getAllUserInfo());
        req.getRequestDispatcher("/userManager.jsp").forward(req, resp);
    }

    private void processOnUser(String userName){
        UserInfoDao dao = new UserInfoDao();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setUserStatus(Integer.valueOf(1));
        dao.updateUserInfo(userInfo);
    }

    private void processOffUser(String userName){
        UserInfoDao dao = new UserInfoDao();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setUserStatus(Integer.valueOf(0));
        dao.updateUserInfo(userInfo);
    }

    private void processDelete(String userName){
        UserInfoDao dao = new UserInfoDao();
        dao.deleteUserInfoByUsername(userName);
    }
}
