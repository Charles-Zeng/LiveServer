package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import com.live.dao.*;

public class DeviceManager  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("设备管理界面");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        String ip = req.getParameter("ip");

        if (action != null){
            TcpServer.getInstance().sendMsgToClient(ip, action);
        }

        DeviceDao dao = new DeviceDao();

        req.setAttribute("devices", dao.getAllDevice());
        req.getRequestDispatcher("/deviceManager.jsp").forward(req, resp);
    }

}
