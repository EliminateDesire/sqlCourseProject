package Dao;
import bean.User;
import utils.DBUtils;

import java.sql.*;
import java.lang.String;

public class userDao {
    public boolean userIsExist(String username) {
        Connection conn = DBUtils.getConnection();
        String sql = "select * from user where username = ?";
        try{
            //获取PreparedStatement对象
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username);//给用户对象属性赋值
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //数据库中存在此用户
                return true;
            }
            //释放资源
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return false;
    }
    public User login(String username,String password) {
        Connection conn = DBUtils.getConnection();
        User user = null;
        String sql = "select * from user where username = ? and password = ?;";

        try {
            //获取PreparedStatement对象
            PreparedStatement ps = conn.prepareStatement(sql);
            //对sql参数进行动态赋值
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();//查询结果集

            //判断数据库中是否存在该用户
            if(rs.next()){
                user = new User();//实例化一个user对象
                //给用户对象赋值
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            //释放资源
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return user;
    }
}
