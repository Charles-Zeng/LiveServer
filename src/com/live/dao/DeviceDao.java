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

    public void updateDevice(Device device){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " update device set  " +
                    " ip = ifnull(?, ip), " +
                    " mac = ifnull(?, mac), " +
                    " imei = ifnull(?, imei), " +
                    " gps = ifnull(?, gps), " +
                    " username = ifnull(?, username), " +
                    " status = ifnull(?, status) " +
                    " where serviceName = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            if (device.getIp() != null){
                pstmt.setString(1, device.getIp());
            }else {
                pstmt.setNull(1, Types.VARCHAR);
            }

            if (device.getMac() != null){
                pstmt.setString(2, device.getMac());
            }else {
                pstmt.setNull(2, Types.VARCHAR);
            }

            if (device.getImei() != null){
                pstmt.setString(3, device.getImei());
            }else {
                pstmt.setNull(3, Types.VARCHAR);
            }

            if (device.getGps() != null){
                pstmt.setString(4, device.getGps());
            }else {
                pstmt.setNull(4, Types.VARCHAR);
            }

            if (device.getUsername() != null){
                pstmt.setString(5, device.getUsername());
            }else {
                pstmt.setNull(5, Types.VARCHAR);
            }

            if (device.getStatus() != null){
                pstmt.setInt(6, device.getStatus());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.setString(7, device.getServiceName());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }finally {
            dbManager.closeConnection(conn);
        }
    }
}
