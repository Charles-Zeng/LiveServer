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
                    " pushAddress, autoStopPushMinutes, userStatus, isAdmin) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userInfo.getUsername());
            pstmt.setString(2, userInfo.getPassword());
            pstmt.setString(3, userInfo.getTel());
            pstmt.setString(4, userInfo.getName());
            pstmt.setString(5, userInfo.getAddress());
            pstmt.setString(6, userInfo.getIdCardNum());
            pstmt.setString(7, userInfo.getPushAddress());
            pstmt.setInt(8, userInfo.getAutoStopPushMinutes().intValue());
            pstmt.setInt(9, userInfo.getUserStatus().intValue());
            pstmt.setInt(10, userInfo.getIsAdmin().intValue());
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
                userInfo.setUserStatus(rs.getInt("userStatus"));
                userInfo.setIsAdmin(rs.getInt("isAdmin"));
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
                userInfo.setUserStatus(rs.getInt("userStatus"));
                userInfo.setIsAdmin(rs.getInt("isAdmin"));

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

    public void updateUserInfo(UserInfo userInfo){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " update user_info set  " +
                    " password = ifnull(?, password), " +
                    " tel = ifnull(?, tel), " +
                    " name = ifnull(?, name), " +
                    " address = ifnull(?, address), " +
                    " idCardNum = ifnull(?, idCardNum), " +
                    " pushAddress = ifnull(?, pushAddress), " +
                    " autoStopPushMinutes = ifnull(?, autoStopPushMinutes), " +
                    " userStatus = ifnull(?, userStatus), " +
                    " isAdmin = ifnull(?, isAdmin) " +
                    " where username = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userInfo.getPassword());
            pstmt.setString(2, userInfo.getTel());
            pstmt.setString(3, userInfo.getName());
            pstmt.setString(4, userInfo.getAddress());
            pstmt.setString(5, userInfo.getIdCardNum());
            pstmt.setString(6, userInfo.getPushAddress());
            pstmt.setInt(7, userInfo.getAutoStopPushMinutes().intValue());
            pstmt.setInt(8, userInfo.getUserStatus().intValue());
            pstmt.setInt(9, userInfo.getIsAdmin().intValue());
            pstmt.setString(10, userInfo.getUsername());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }
}
