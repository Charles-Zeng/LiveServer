package com.live.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceListener implements ServletContextListener{
    @Override
    public void contextDestroyed(ServletContextEvent arg) {
        TcpServer.getInstance().stop();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg) {
        TcpServer.getInstance().start();
    }
}