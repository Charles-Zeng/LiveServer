import java.sql.*;
import java.io.*;
import java.util.Properties;


public class DBManager {
    // DB config info
    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;

    public DBManager(){
        try{
            Properties prop = new Properties();
            InputStream inputStream = DBManager.class.getClassLoader().getResourceAsStream("/db.properties");
            prop.load(inputStream);
            jdbcDriver = prop.getProperty("driver");
            dbUrl = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(dbUrl, user, password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

    public void closeConnection(Connection conn){
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection conn, Statement stmt){
        try{
            stmt.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection conn, Statement stmt, ResultSet rs){
        try{
            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
