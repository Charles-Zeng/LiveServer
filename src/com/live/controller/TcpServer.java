package com.live.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.live.dao.DeviceDao;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.core.session.IoSession;

public class TcpServer {

    private static final String ipAttribute = "KEY_SESSION_CLIENT_IP";
    private static TcpServer instance = new TcpServer();
    private NioSocketAcceptor acceptor;

    private TcpServer(){

    }

    public static TcpServer getInstance(){
        return instance;
    }

    public void start(){
        try {
            //第一步 创建一个NioSocketAcceptor 对象
            acceptor = new NioSocketAcceptor();
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
            //第二步设置handler
            acceptor.setHandler(new TcpHandler());
            //第三步,获取拦截器，发来的消息都需要通过拦截器拦截之后才能接收到
            acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
            //添加一个拦截器对数据进行加解码TextLineCodecFactory()
            acceptor.getFilterChain().addLast("codeFilter"
                    ,new ProtocolCodecFilter(new TextLineCodecFactory()));;
            //第四步，绑定端口号
            acceptor.bind(new InetSocketAddress(9800));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stop(){
        acceptor.dispose();
        DeviceDao dao = new DeviceDao();
        dao.dropDeviceAll();
        System.out.println("TcpServer:stop");
    }

    void sendMsgToClient(String ip, String msg){
        System.out.println("sendMsgToClient: ip " + ip + " msg " + msg);
        for (IoSession ioSession : acceptor.getManagedSessions().values()){
            if (ip.equals((String)ioSession.getAttribute(ipAttribute))){
                ioSession.write(msg);
            }
        }
    }
}
