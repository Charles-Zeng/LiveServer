package com.live.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class SocketThread extends Thread
{
    public static final int PORT = 9800;//监听的端口号
    private static  ServerSocket serverSocket = null;
    public SocketThread(ServerSocket serverScoket){
        try {
            if(null == serverSocket){
                this.serverSocket = new ServerSocket(PORT);
                System.out.println("socket start");
            }
        } catch (Exception e) {
            System.out.println("SocketThread创建socket服务出错");
            e.printStackTrace();
        }
    }
    //单例
    /*private static  SocketThread  instance;
    public static SocketThread getInstance(){
        if(instance == null){
            try {
                instance = new SocketThread(serverSocket);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return instance;
    }*/
    public void run(){
        while(true){
            try {
                if(serverSocket==null){
                    break;
                }else if(serverSocket.isClosed()){
                    break;
                }
                Socket socket = serverSocket.accept();
                if(null != socket && !socket.isClosed()){
                    //处理接受的数据
                    Thread t = new Thread(new SocketOperate(socket));
                    t.run();
                }else{
                    break;
                }
            }catch (Exception e) {
                System.out.println("SocketThread创建socket服务出错");
                e.printStackTrace();
            }
        }
    }
    public void SendCmd(){
        while(true){
            try {
                Socket socket = serverSocket.accept();
                if(null != socket && !socket.isClosed()){
                    //处理接受的数据
                    SocketOperate sockOper = new SocketOperate(socket);
                    sockOper.getInstance().SendCmd();
                }else{
                    break;
                }
            }catch (Exception e) {
                System.out.println("SocketThread创建socket服务出错");
                e.printStackTrace();
            }
        }
    }
    public void closeSocketServer(){
        try {
            if(null!=serverSocket && !serverSocket.isClosed())
            {
                serverSocket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

