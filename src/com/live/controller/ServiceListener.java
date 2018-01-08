package com.live.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class ServiceListener implements ServletContextListener{
    private SocketThread socketThread;
    public void contextDestroyed(ServletContextEvent arg) {
        if(null!=socketThread && !socketThread.isInterrupted())
        {
            socketThread.closeSocketServer();
            socketThread.interrupt();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg) {
        // TODO Auto-generated method stub
        if(null==socketThread)
        {
            //新建线程类
            socketThread=new SocketThread(null);
            //启动线程
            socketThread.start();
        }
    }
}