package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import com.live.dao.*;
import com.live.model.DeviceHistory;
import com.live.model.UserInfo;

public class DeviceHistoryManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("设备历史记录管理界面");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // user login status check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userInfo") == null){
            resp.sendRedirect("/login.jsp");
            return;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        String username = req.getParameter("username");
        String serviceName = req.getParameter("serviceName");
        if (userInfo.getIsAdmin() == 0) {
            username = userInfo.getUsername();
        }

        DeviceHistoryDao dao = new DeviceHistoryDao();
        List<DeviceHistory> deviceHistories;
        if (username != null || serviceName != null){
            deviceHistories = filterDeviceHistories(username, serviceName);
        } else {
            deviceHistories = dao.getAllDeviceHistory();
        }

        req.setAttribute("deviceHistories", deviceHistories);
        req.getRequestDispatcher("/deviceHistoryManager.jsp").forward(req, resp);
    }

    private List<DeviceHistory> filterDeviceHistories(String username, String serviceName){
        DeviceHistoryDao dao = new DeviceHistoryDao();
        return dao.filterDeviceHistories(username, serviceName);
    }
}
