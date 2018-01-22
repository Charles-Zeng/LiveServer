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
            String sql = " insert into device(ip, mac, imei, gps, serviceName, username, status) " +
                    " values (?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, device.getIp());
            pstmt.setString(2, device.getMac());
            pstmt.setString(3, device.getImei());
            pstmt.setString(4, device.getGps());
            pstmt.setString(5, device.getServiceName());
            pstmt.setString(6, device.getUsername());
            pstmt.setInt(7, device.getStatus());
            pstmt.executeUpdate();
            pstmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }

    public Device getDeviceByServiceName(String serviceName){
        Device device = null;
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = "select * from device where serviceName = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, serviceName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                device = new Device();
                device.setIp(rs.getString("ip"));
                device.setMac(rs.getString("mac"));
                device.setImei(rs.getString("imei"));
                device.setGps(rs.getString("gps"));
                device.setServiceName(rs.getString("serviceName"));
                device.setUsername(rs.getString("username"));
                device.setStatus(rs.getInt("status"));
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

    public List<Device> getAllDevice(){
        List<Device> devices = new ArrayList<Device>();
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = "select * from device";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Device device = new Device();
                device.setIp(rs.getString("ip"));
                device.setMac(rs.getString("mac"));
                device.setImei(rs.getString("imei"));
                device.setGps(rs.getString("gps"));
                device.setServiceName(rs.getString("serviceName"));
                device.setUsername(rs.getString("username"));
                device.setStatus(rs.getInt("status"));
                devices.add(device);
            }

            rs.close();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }

        return devices;
    }

    public void deleteDeviceByIp(String ip){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " DELETE FROM device WHERE ip = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ip);
            pstmt.executeUpdate();
            pstmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }

    public void updateDeviceStatusByIp(String ip, int status){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " UPDATE device SET status = ? WHERE ip = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,status);
            pstmt.setString(2, ip);
            pstmt.executeUpdate();
            pstmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }

    public Device getDeviceByUserName(String username){
        Device device = null;
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = "select * from device where username = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                device = new Device();
                device.setIp(rs.getString("ip"));
                device.setMac(rs.getString("mac"));
                device.setImei(rs.getString("imei"));
                device.setGps(rs.getString("gps"));
                device.setServiceName(rs.getString("serviceName"));
                device.setUsername(rs.getString("username"));
                device.setStatus(rs.getInt("status"));
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

    public List<Device> filterDevices(String username, String serviceName){
        List<Device> devices = new ArrayList<Device>();
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " select * from device where username like ? " +
                    "and serviceName like ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            if (username != null){
                pstmt.setString(1, "%" + username + "%");
            } else {
                pstmt.setString(1, "%");
            }

            if (serviceName != null){
                pstmt.setString(2, "%" + serviceName + "%");
            } else {
                pstmt.setString(2, "%");
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Device device = new Device();
                device.setIp(rs.getString("ip"));
                device.setMac(rs.getString("mac"));
                device.setImei(rs.getString("imei"));
                device.setGps(rs.getString("gps"));
                device.setServiceName(rs.getString("serviceName"));
                device.setUsername(rs.getString("username"));
                device.setStatus(rs.getInt("status"));
                devices.add(device);
            }

            rs.close();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }

        return devices;
    }
}
