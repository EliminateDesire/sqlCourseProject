package Dao;

import bean.Course;
import utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class operateDao {
    //查询所有开设的课程
    public static ArrayList<Course> allCourseQuery() {
        Connection conn = DBUtils.getConnection();
        String sql = "select * from course";
        ArrayList<Course> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            //给用户对象属性赋值
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Course course = new Course();
                course.setCourseNum(rs.getString("kh"));
                course.setCourseName(rs.getString("km"));
                course.setTeacherNum(rs.getInt("jsh"));
                course.setTeacherName(rs.getString("jsm"));
                course.setCourseCredit(rs.getInt("xf"));
                course.setCourseTime(rs.getString("sj"));
                course.setCoursePlace(rs.getString("dd"));
                list.add(course);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
        return list;
    }

    public static boolean selectSuccess(String courseNum, String teacherNum, String username) {
        Connection conn = DBUtils.getConnection();
        int result = 0;
        String sql = "select * from course where kh = ? and jsh = ?";
        try {
            //获取PreparedStatement对象
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, courseNum);
            ps.setString(2, teacherNum);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //数据库中存在这门课
                String sql2 = "insert into selectCourse values(?,?,?)";
                PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
                ps2.setString(1, username);
                ps2.setString(2, courseNum);
                ps2.setString(3, teacherNum);
                result = ps2.executeUpdate();
                ps2.close();
            }
            //释放资源
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
        return result == 1;
    }
}
//    public static void main(String[] args) {
//        ArrayList<Course>list = new ArrayList<>();
//        list = operateDao.allCourseQuery();
//        for(Course x:list){
//            System.out.println(x.getTeacherName());
//        }
//    }

