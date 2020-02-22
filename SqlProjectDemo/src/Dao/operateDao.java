package Dao;

import bean.Course;
import com.mysql.cj.Session;
import utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

//操作的业务逻辑代码，包括学生选课，退课，教师开课，录入成绩
public class operateDao {
    //选课之前，先查询所有开设的课程
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

    //是否选课成功
    public static boolean selectSuccess(String courseNum, String teacherNum, String username) {
        Connection conn = DBUtils.getConnection();
        int selectResult = 0;
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
                selectResult = ps2.executeUpdate();
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
        return selectResult == 1;
    }

    //退课之前，先查询已选课程
    public static ArrayList<Course> haveSelectQuery(String username) {
        Connection conn = DBUtils.getConnection();
        String sql = "select c.kh,km,c.jsh,jsm,xf from course as c join selectcourse as s\n" +
                "where s.xh = ? and s.kh = c.kh;";
        ArrayList<Course> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            //给用户对象属性赋值
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseNum(rs.getString("kh"));
                course.setCourseName(rs.getString("km"));
                course.setTeacherNum(rs.getInt("jsh"));
                course.setTeacherName(rs.getString("jsm"));
                course.setCourseCredit(rs.getInt("xf"));
                list.add(course);
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
        return list;
    }

    //是否退课成功
    public static boolean quitSuccess(String courseNum, String teacherNum, String username) {
        Connection conn = DBUtils.getConnection();
        int quitResult = 0;
        String sql = "delete from selectcourse where xh = ? and kh = ? and jsh = ?;";
        try {
            //获取PreparedStatement对象
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, courseNum);
            ps.setString(3, teacherNum);
            System.out.println(ps.toString());
            quitResult = ps.executeUpdate();
            //释放资源
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
        return quitResult == 1;
    }

    //是否开课成功
    public static boolean openSuccess(String courseNum, String courseName,
            int teacherNum,String teacherName,int courseCredit, String courseTime,String coursePlace) {
        Connection conn = DBUtils.getConnection();
        int openResult = 0;
        String sql = "insert into course values (?,?,?,?,?,?,?)";
        try {
            //获取PreparedStatement对象
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, courseNum);
            ps.setString(2, courseName);
            ps.setInt(3, teacherNum);
            ps.setString(4, teacherName);
            ps.setInt(5, courseCredit);
            ps.setString(6, courseTime);
            ps.setString(7, coursePlace);
            openResult = ps.executeUpdate();
            //释放资源
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
        return openResult == 1;
    }

    //是否提交成绩成功
    public static boolean submitSuccess (String courseNum,String studentNum,int courseGrade,String username){
        Connection conn = DBUtils.getConnection();
        int submitResult = 0;
        String sql = "insert into grade values (?,?,?,?,?,?)";
        try {
            //获取PreparedStatement对象
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            int teacherNum = queryDao.teaNumQuery(courseNum,studentNum);
            Course course = queryDao.courseQuery(courseNum,teacherNum);

            ps.setString(1, studentNum);
            ps.setString(2, courseNum);
            ps.setString(3, course.getCourseName());
            ps.setString(4, queryDao.nameQuery(username));
            ps.setInt(5, course.getCourseCredit());
            ps.setInt(6, courseGrade);
            submitResult = ps.executeUpdate();
            //释放资源
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
        return submitResult == 1;
    }
}
//    public static void main(String[] args) {
////        ArrayList<Course>list = new ArrayList<>();
//        boolean ans = operateDao.quitSuccess("08300001","1001","17121439");
//        System.out.println(ans);
//    }
//}

