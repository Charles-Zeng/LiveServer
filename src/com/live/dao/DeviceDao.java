package com.live.dao;

import java.sql.*;
import java.util.*;
import com.live.model.*;
import com.live.util.*;

public class DeviceDao {

    public void addDevice(Device device){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " insert into device(ip, mac, imei, gps, serviceName, username) " +
                    " values (?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, device.getIp());
            pstmt.setString(2, device.getMac());
            pstmt.setString(3, device.getImei());
            pstmt.setString(4, device.getGps());
            pstmt.setString(5, device.getServiceName());
            pstmt.setString(6, device.getUsername());
            pstmt.executeUpdate();
            pstmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }

    public Device getDeviceByServiceName(String serviceName){
        Device device = new Device();
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = "select * from device where serviceName = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, serviceName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                device.setIp(rs.getString("ip"));
                device.setMac(rs.getString("mac"));
                device.setImei(rs.getString("imei"));
                device.setGps(rs.getString("gps"));
                device.setServiceName(rs.getString("serviceName"));
                device.setUsername(rs.getString("username"));
            }

            rs.close();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }

        return device;
    }
}
