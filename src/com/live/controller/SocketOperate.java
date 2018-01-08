package com.live.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class SocketOperate implements Runnable {
    private static Socket socket;
    //该线程所处理的Socket所对应的输入流
    BufferedReader br = null;
    String str = null;
    String content = null;
    InputStreamReader reader=null;
    public final static String LoginType = "Login"; //登陆请求
    public final static String HeartType = "Heart"; //心跳请求
    //单例
    private static  SocketOperate  instance;
    public static SocketOperate getInstance(){
        if(instance == null){
            synchronized(SocketThread.class){
                try {
                    instance = new SocketOperate(socket);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

    public SocketOperate(Socket socket) throws IOException
    {
        this.socket = socket;
        reader = new InputStreamReader(this.socket.getInputStream(),"utf-8");
        br = new BufferedReader(reader);
    }

    @Override
    public void run()
    {
        try {
            // 采用循环不断从Socket中读取客户端发送过来的数据
            while (true)
            {
                content = readFromClient();
                System.out.println(content);
                if (content == null)
                {
                    break;
                }
                PraseRecvPakge(content);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    //定义读取客户端数据的方法
    private String readFromClient()
    {
        try
        {
            str = br.readLine();
            return str;
        }
        //如果捕捉到异常，表明该Socket对应的客户端已经关闭
        catch (IOException e)
        {
            try {
                br.close();
                reader.close();
                socket.close();
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
    //解析客户端发送过来的json包体
    private boolean PraseRecvPakge(String RecvData) throws JSONException
    {

        //创建JSON解析对象(两条规则的体现:大括号用JSONObject,注意传入数据对象)
        JSONObject obj = JSONObject.parseObject(RecvData);
        //obj.后面有各种数据类型,根据对象来选择使用的数据类型
        String RequsetType = obj.getString("RequsetTpye");
        //登陆请求
        if (RequsetType.equals(LoginType))
        {
            //同理如上，这里的age为Int类型，我们就用对应的类型进行解析
            String name = obj.getString("name");
            String pwd = obj.getString("pwd");
            //最后输出到控制台
            System.out.println(name+"+"+pwd);
            //回复客户端应答
            try {
                SendLogin();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //心跳请求
        if (RequsetType.equals(HeartType))
        {
            try {
                SendHeart();
                System.out.println("心跳保持。。。。");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }
    private void SendLogin() throws IOException
    {
        JSONObject LoginObject = new JSONObject();
        LoginObject.put("LoginRep", "LOGIN_OK");
        try {
            OutputStream LoginOS = socket.getOutputStream();
            String loginStr = LoginObject.toString() + "\n";
            LoginOS.write(loginStr.getBytes("utf-8"));
            LoginOS.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void SendHeart() throws IOException
    {
        JSONObject HeratObject = new JSONObject();
        HeratObject.put("TYPE", "HEART");
        HeratObject.put("HeartRep", "HEART_OK");
        try {
            OutputStream HeartOS = socket.getOutputStream();
            String loginStr = HeratObject.toString() + "\n";
            HeartOS.write(loginStr.getBytes("utf-8"));
            HeartOS.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public  void SendCmd() throws IOException
    {
        JSONObject CmdObject = new JSONObject();
        CmdObject.put("TYPE", "CMD");
        CmdObject.put("CmdReq", "STOP");
        try {
            OutputStream CmdOS = socket.getOutputStream();
            String loginStr = CmdObject.toString() + "\n";
            CmdOS.write(loginStr.getBytes("utf-8"));
            CmdOS.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

