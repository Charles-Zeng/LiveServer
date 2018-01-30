package com.live.controller;

import java.net.*;

import com.alibaba.fastjson.JSONObject;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSON;
import com.live.model.*;
import com.live.dao.*;

public class TcpHandler extends IoHandlerAdapter{

    private static final String ipAttribute = "KEY_SESSION_CLIENT_IP";

    //异常的处理
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        super.exceptionCaught(session, cause);
        System.out.println("exceptionCaught " + cause);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.inputClosed(session);
        System.out.println("inputClosed");
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        // TODO Auto-generated method stub
        super.messageReceived(session, message);
        System.out.println("messageReceived:" + message);
        JSONObject jsonObject = JSON.parseObject(message.toString());
        String pkgType = jsonObject.getString("Type");

        if (pkgType.equals("Login")){
            //LoginReq loginReq = JSON.parse(message.toString(), LoginReq.class);
            String resp = processLogin(session.getAttribute(ipAttribute).toString(), message.toString());
            session.write(resp + "\r\n");
        } else if (pkgType.equals("StatusPush")) {
            processStatusPush(session.getAttribute(ipAttribute).toString(), message.toString());
        } else if (pkgType.equals("LoginGps")){
            processLoginGps(session.getAttribute(ipAttribute).toString(), message.toString());
        } else if (pkgType.equals("Heart")){
            JSONObject HeartJson = new JSONObject();
            HeartJson.put("Type", "Heart");
            session.write(HeartJson.toString() + "\r\n");
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        super.messageSent(session, message);
        System.out.println(((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress() + " messageSent:" + message);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionClosed(session);
        System.out.println("sessionClosed");
        DeviceDao dao = new DeviceDao();
        dao.deleteDeviceByIp(session.getAttribute(ipAttribute).toString());
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionCreated(session);
        String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
        session.setAttribute(ipAttribute, clientIP);
        System.out.println("sessionCreated");
    }
    // 这个方法是在服务器空闲状态的时候调用的
    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        // TODO Auto-generated method stub
        super.sessionIdle(session, status);
        System.out.println("sessionIdle");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionOpened(session);
        System.out.println("sessionOpened");
    }

    private String processLogin(String clientIp, String req){
        JSONObject reqJson = JSON.parseObject(req);
        JSONObject respJson = new JSONObject();

        respJson.put("Type", "LoginResp");

        if (!validUser(reqJson.getString("UserName"), reqJson.getString("UserPwd"))){
            respJson.put("Status", "Failed");
            respJson.put("ErrorCode", "-1");
            respJson.put("Message", "invalid username or password");
            return respJson.toString();
        }

        if (!validServiceName(reqJson.getString("ServiceName"))){
            respJson.put("Status", "Failed");
            respJson.put("ErrorCode", "-2");
            respJson.put("Message", "serviceName already exist");
            return respJson.toString();
        }

        Device device = new Device();
        device.setIp(clientIp);
        device.setUsername(reqJson.getString("UserName"));
        device.setServiceName(reqJson.getString("ServiceName"));
        device.setMac(reqJson.getString("Mac"));
        device.setImei(reqJson.getString("Imei"));
        device.setGps(reqJson.getString("Gps"));
        // 默认状态为开始采集
        device.setStatus(1);

        DeviceDao dao = new DeviceDao();


        if (dao.getDeviceByServiceName(device.getServiceName()) != null){
            respJson.put("Status", "Failed");
            return respJson.toString();
        }

        dao.addDevice(device);

        UserInfoDao userDao = new UserInfoDao();
        UserInfo userInfo = userDao.getUserInfoByUsername(reqJson.getString("UserName"));
        respJson.put("AutoStopPushMinutes", String.valueOf(userInfo.getAutoStopPushMinutes()));
        respJson.put("Status", "Ok");
        return respJson.toString();
    }

    private boolean validUser(String userName, String password){
        UserInfoDao dao = new UserInfoDao();
        UserInfo userInfo = dao.getUserInfoByUsername(userName);
        if (userInfo != null && password.equals(userInfo.getPassword())){
            return true;
        }

        return false;
    }

    private boolean validServiceName(String serviceName){
        DeviceDao dao = new DeviceDao();
        Device device = dao.getDeviceByServiceName(serviceName);
        if (device == null){
            return true;
        }

        return false;
    }

    private void processStatusPush(String clientIp, String req){
        JSONObject reqJson = JSON.parseObject(req);

        String pushStatus = reqJson.getString("Status");
        int status = 1;
        if ("Stoped".equals(pushStatus)){
            status = 0;
        }

        DeviceDao dao = new DeviceDao();
        dao.updateDeviceStatusByIp(clientIp, status);
    }

    private void processLoginGps(String clientIp, String req){
        JSONObject reqJson = JSON.parseObject(req);

        String gps = reqJson.getString("Gps");
        String serviceName = reqJson.getString("ServiceName");

        DeviceDao dao = new DeviceDao();
        Device device = new Device();
        device.setGps(gps);
        device.setServiceName(serviceName);
        dao.updateDevice(device);
    }

}
