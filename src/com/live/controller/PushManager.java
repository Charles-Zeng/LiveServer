package com.live.controller;

import com.alibaba.fastjson.JSONObject;
import com.live.dao.DeviceDao;
import com.live.model.Device;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PushManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("推送管理界面");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String serviceName = req.getParameter("serviceName");

        DeviceDao dao = new DeviceDao();
        Device device = dao.getDeviceByServiceName(serviceName);

        if (device == null){
            System.out.println("not exist serviceName:" + serviceName);
            resp.sendError(400, "not exist push address");
            return;
        }

        if (device.getStatus() == 1){
            //已经开启了推流，直接返回
            return;
        }

        JSONObject cmdJson = new JSONObject();
        cmdJson.put("Type", "Cmd");
        cmdJson.put("Operator", "On");
        TcpServer.getInstance().sendMsgToClient(device.getIp(), cmdJson.toString() + "\r\n");
        dao.updateDeviceStatusByIp(device.getIp(), 1);
        System.out.println("start push, serviceName:" + serviceName);
    }
}
