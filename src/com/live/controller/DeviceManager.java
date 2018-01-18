package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.live.dao.*;
import com.live.model.Device;

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

        // Set refresh, autoload time as 5 seconds
        //resp.setIntHeader("Refresh", 3);

        String action = req.getParameter("action");
        String ip = req.getParameter("ip");

        if (action != null && ip != null){
            String operator;
            int status = 0;
            if (action.equals("switchOn")){
                operator = "On";
                status = 1;
            } else {
                operator = "Off";
                status = 0;
            }
            JSONObject cmdJson = new JSONObject();
            cmdJson.put("Type", "Cmd");
            cmdJson.put("Operator", operator);
            TcpServer.getInstance().sendMsgToClient(ip, cmdJson.toString() + "\r\n");
            DeviceDao updateDao = new DeviceDao();
            updateDao.updateDeviceStatusByIp(ip, status);
        }

        DeviceDao dao = new DeviceDao();

        req.setAttribute("devices", dao.getAllDevice());
        req.getRequestDispatcher("/deviceManager.jsp").forward(req, resp);
    }

}
