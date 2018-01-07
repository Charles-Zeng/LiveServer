package com.live.dao;

import java.sql.*;
import java.util.*;
import com.live.model.*;
import com.live.util.*;

public class UserInfoDao {

    public void addUserInfo(UserInfo userInfo){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " insert into user_info(username, password, tel, name, address, idCardNum, " +
                    " pushAddress, autoStopPushMinutes) values (?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userInfo.getUsername());
            pstmt.setString(2, userInfo.getPassword());
            pstmt.setString(3, userInfo.getTel());
            pstmt.setString(4, userInfo.getName());
            pstmt.setString(5, userInfo.getAddress());
            pstmt.setString(6, userInfo.getIdCardNum());
            pstmt.setString(7, userInfo.getPushAddress());
            pstmt.setInt(8, userInfo.getAutoStopPushMinutes().intValue());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }

    public UserInfo getUserInfoByUsername(String username){
        UserInfo userInfo = null;
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " SELECT * FROM user_info WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                userInfo = new UserInfo();
                userInfo.setUsername(rs.getString("username"));
                userInfo.setPassword(rs.getString("password"));
                userInfo.setTel(rs.getString("tel"));
                userInfo.setName(rs.getString("name"));
                userInfo.setAddress(rs.getString("address"));
                userInfo.setIdCardNum(rs.getString("idCardNum"));
                userInfo.setPushAddress(rs.getString("pushAddress"));
                userInfo.setAutoStopPushMinutes(rs.getInt("autoStopPushMinutes"));
            }
            rs.close();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }

        return userInfo;
    }

    public List<UserInfo> getAllUserInfo(){
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " SELECT * FROM user_info";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(rs.getString("username"));
                userInfo.setPassword(rs.getString("password"));
                userInfo.setTel(rs.getString("tel"));
                userInfo.setName(rs.getString("name"));
                userInfo.setAddress(rs.getString("address"));
                userInfo.setIdCardNum(rs.getString("idCardNum"));
                userInfo.setPushAddress(rs.getString("pushAddress"));
                userInfo.setAutoStopPushMinutes(rs.getInt("autoStopPushMinutes"));

                userInfoList.add(userInfo);
            }
            rs.close();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }

        return userInfoList;
    }
}
