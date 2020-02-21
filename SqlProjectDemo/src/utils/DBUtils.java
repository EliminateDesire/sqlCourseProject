package utils;
import java.sql.*;
public class DBUtils {

    /**
     * 获取数据库连接
     * @return Connection对象
     */
    private static final String dbUserName = "root";
    private static final String dbPassword = "123456";
    private static final String dbURL = "jdbc:mysql://127.0.0.1:3306/school?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";

//    public static Connection getConnection() throws Exception{
//        if(conn == null)
//            conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
//        return conn;
//    }

    public static Connection getConnection(){
        Connection conn = null;
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
    		if(conn == null)
    			conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn Connection对象
     */
    public static void closeConnection(Connection conn) {
        //判断conn是否为空
        if(conn != null){
            try {
                conn.close();//关闭数据库连接
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
