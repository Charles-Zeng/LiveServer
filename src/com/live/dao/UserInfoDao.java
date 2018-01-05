package com.live.dao;

import java.sql.*;
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
}
