package com.live.controller;

import java.net.*;

import com.alibaba.fastjson.JSONObject;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSON;
import com.live.model.*;

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
        String pkgType = jsonObject.getString("RequsetTpye");

        if (pkgType.equals("Login")){
            //LoginReq loginReq = JSON.parse(message.toString(), LoginReq.class);
            JSONObject respJson = new JSONObject();
            respJson.put("TYPE", "LOGIN");
            respJson.put("LoginRep", "LOGIN_OK");
            session.write(respJson.toString() + "\r\n");
        }else if (pkgType.equals("Heart")){
            JSONObject HeratObject = new JSONObject();
            HeratObject.put("TYPE", "HEART");
            HeratObject.put("HeartRep", "HEART_OK");
            session.write(HeratObject.toString() + "\r\n");
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

}
