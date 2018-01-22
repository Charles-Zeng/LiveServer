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

            if (userInfo.getPassword() != null){
                pstmt.setString(1, userInfo.getPassword());
            }else {
                pstmt.setNull(1, Types.VARCHAR);
            }

            if (userInfo.getTel() != null){
                pstmt.setString(2, userInfo.getTel());
            }else {
                pstmt.setNull(2, Types.VARCHAR);
            }

            if (userInfo.getName() != null){
                pstmt.setString(3, userInfo.getName());
            }else {
                pstmt.setNull(3, Types.VARCHAR);
            }

            if (userInfo.getAddress() != null){
                pstmt.setString(4, userInfo.getAddress());
            }else {
                pstmt.setNull(4, Types.VARCHAR);
            }

            if (userInfo.getIdCardNum() != null){
                pstmt.setString(5, userInfo.getIdCardNum());
            }else {
                pstmt.setNull(5, Types.VARCHAR);
            }

            if (userInfo.getPushAddress() != null){
                pstmt.setString(6, userInfo.getPushAddress());
            }else {
                pstmt.setNull(6, Types.VARCHAR);
            }

            if (userInfo.getAutoStopPushMinutes() != null){
                pstmt.setInt(7, userInfo.getAutoStopPushMinutes());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }

            if (userInfo.getUserStatus() != null){
                pstmt.setInt(8, userInfo.getUserStatus());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }

            if (userInfo.getIsAdmin() != null){
                pstmt.setInt(9, userInfo.getIsAdmin());
            } else {
                pstmt.setNull(9, Types.INTEGER);
            }

            pstmt.setString(10, userInfo.getUsername());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }

    public void deleteUserInfoByUsername(String userName){
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        try{
            String sql = " delete from user_info where username = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbManager.closeConnection(conn);
        }
    }
}
