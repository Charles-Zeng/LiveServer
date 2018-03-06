package com.live.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

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

        // user login status check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userInfo") == null){
            resp.sendRedirect("/login.jsp");
            return;
        }

        // Set refresh, autoload time as 5 seconds
        //resp.setIntHeader("Refresh", 3);

        String action = req.getParameter("action");
        String ip = req.getParameter("ip");

        DeviceDao dao = new DeviceDao();

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

            Device device = dao.getDeviceByIp(ip);

            if (!device.getStatus().equals(status)) {
                JSONObject cmdJson = new JSONObject();
                cmdJson.put("Type", "Cmd");
                cmdJson.put("Operator", operator);
                TcpServer.getInstance().sendMsgToClient(ip, cmdJson.toString() + "\r\n");
                dao.updateDeviceStatusByIp(ip, status);
            }
        }

        String username = req.getParameter("username");
        String serviceName = req.getParameter("serviceName");

        List<Device> devices;
        if (username != null || serviceName != null){
            devices = filterDevices(username, serviceName);
        } else {
            devices = dao.getAllDevice();
        }

        System.out.println("devices:" + devices);
        req.setAttribute("devices", devices);
        req.getRequestDispatcher("/deviceManager.jsp").forward(req, resp);
    }

    private List<Device> filterDevices(String username, String serviceName){
        DeviceDao dao = new DeviceDao();
        return dao.filterDevices(username, serviceName);
    }

}
