package com.live.dao;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;
import com.live.model.*;
import com.live.util.*;
import java.text.SimpleDateFormat;

public class DeviceHistoryDao {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void addDeviceHistory(DeviceHistory deviceHistory){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " insert into device_history(username, ip, mac, imei, gps, " +
                    " service_name, login_time, logout_time, duration_time) " +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deviceHistory.getUsername());
            pstmt.setString(2, deviceHistory.getIp());
            pstmt.setString(3, deviceHistory.getMac());
            pstmt.setString(4, deviceHistory.getImei());
            pstmt.setString(5, deviceHistory.getGps());
            pstmt.setString(6, deviceHistory.getServiceName());
            pstmt.setString(7,dateFormat.format(deviceHistory.getLoginTime()));
            pstmt.setString(8,dateFormat.format(deviceHistory.getLogoutTime()));
            pstmt.setLong(9,deviceHistory.getDurationTime());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }

    public List<DeviceHistory> getAllDeviceHistory(){
        List<DeviceHistory> deviceHistories = new ArrayList<DeviceHistory>();
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = "select * from device_history";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                DeviceHistory deviceHistory = new DeviceHistory();
                deviceHistory.setUsername(rs.getString("username"));
                deviceHistory.setIp(rs.getString("ip"));
                deviceHistory.setMac(rs.getString("mac"));
                deviceHistory.setImei(rs.getString("imei"));
                deviceHistory.setGps(rs.getString("gps"));
                deviceHistory.setServiceName(rs.getString("service_name"));
                deviceHistory.setLoginTime(dateFormat.parse(rs.getString("login_time")));
                deviceHistory.setLogoutTime(dateFormat.parse(rs.getString("logout_time")));
                deviceHistory.setDurationTime(rs.getLong("duration_time"));
                deviceHistories.add(deviceHistory);
            }

            rs.close();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            dbManager.closeConnection(conn);
        }

        return deviceHistories;
    }

    public List<DeviceHistory> filterDeviceHistories(String username, String serviceName){
        List<DeviceHistory> deviceHistories = new ArrayList<DeviceHistory>();
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " select * from device_history where username like ? " +
                    "and service_name like ? ";

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
                DeviceHistory deviceHistory = new DeviceHistory();
                deviceHistory.setUsername(rs.getString("username"));
                deviceHistory.setIp(rs.getString("ip"));
                deviceHistory.setMac(rs.getString("mac"));
                deviceHistory.setImei(rs.getString("imei"));
                deviceHistory.setGps(rs.getString("gps"));
                deviceHistory.setServiceName(rs.getString("service_name"));
                deviceHistory.setLoginTime(dateFormat.parse(rs.getString("login_time")));
                deviceHistory.setLogoutTime(dateFormat.parse(rs.getString("logout_time")));
                deviceHistory.setDurationTime(rs.getLong("duration_time"));
                deviceHistories.add(deviceHistory);
            }

            rs.close();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            dbManager.closeConnection(conn);
        }

        return deviceHistories;
    }
}
