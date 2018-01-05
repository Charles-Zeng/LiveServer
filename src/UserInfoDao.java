import java.sql.*;

public class UserInfoDao {
    public void addUserInfo(UserInfo userInfo){
        try{
            DBManager dbManager = new DBManager();
            Connection conn = dbManager.getConnection();

            String sql = "insert into user_info(username, password, tel) values (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userInfo.getUsername());
            pstmt.setString(2, userInfo.getPassword());
            pstmt.setString(3, userInfo.getTel());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
