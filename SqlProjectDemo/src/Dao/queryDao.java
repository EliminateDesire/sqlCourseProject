package Dao;

import utils.DBUtils;
import bean.courseGrade;
import bean.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class queryDao {
    //根据学号或者工号查名字
    public static String nameQuery(String username){
        Connection conn = DBUtils.getConnection();
        String res = null;
        try{
            //学生
            if(username.length() == 8){
                String sql = "select * from stu where xh = ?";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                //给用户对象属性赋值
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    res = rs.getString("xm");
                }
                ps.close();
                rs.close();
            }else{ //老师
                String sql = "select * from tea where gh = ?";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                //给用户对象属性赋值
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    res = rs.getString("xm");
                }
                ps.close();
                rs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return res;
    }
    //学生查询成绩
    public static ArrayList<courseGrade> stuGradeQuery(String username){
        Connection conn = DBUtils.getConnection();
        String sql = "select * from grade where xh = ?";
        ArrayList<courseGrade>list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            //给用户对象属性赋值
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                courseGrade coursegrade = new courseGrade();
                coursegrade.setCourseNum(rs.getString("kh"));
                coursegrade.setCourseName(rs.getString("km"));
                coursegrade.setTeacher(rs.getString("js"));
                coursegrade.setCourseCredit(rs.getInt("xf"));
                coursegrade.setCourseGrade(rs.getInt("cj"));
                list.add(coursegrade);
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return list;
    }

    //学生查询所选课程
    public static ArrayList<Course> stuCourseQuery(String username){
        Connection conn = DBUtils.getConnection();
        String sql = "select km,jsm,xf,sj,dd from course as c join selectcourse as s\n" +
                "where s.xh = ? and s.kh = c.kh;";
        ArrayList<Course>list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            //给用户对象属性赋值
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setCourseName(rs.getString("km"));
                course.setTeacherName(rs.getString("jsm"));
                course.setCourseCredit(rs.getInt("xf"));
                course.setCourseTime(rs.getString("sj"));
                course.setCoursePlace(rs.getString("dd"));
                list.add(course);
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return list;
    }

    //教师查询成绩
    public static ArrayList<courseGrade> teaGradeQuery(String username){
        Connection conn = DBUtils.getConnection();
        String teacherName = queryDao.nameQuery(username);
        String sql = "select * from grade where js = ? order by xh";
        ArrayList<courseGrade>list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            //给用户对象属性赋值
            ps.setString(1, teacherName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                courseGrade coursegrade = new courseGrade();
                coursegrade.setCourseNum(rs.getString("kh"));
                coursegrade.setCourseName(rs.getString("km"));
                coursegrade.setStudentNum(rs.getString("xh"));
                coursegrade.setStudentName(queryDao.nameQuery(rs.getString("xh")));
                coursegrade.setCourseGrade(rs.getInt("cj"));
                list.add(coursegrade);
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return list;
    }
    //教师查询自己开的课
    public static ArrayList<Course> teaCourseQuery(String username){
        Connection conn = DBUtils.getConnection();
        String sql = "select * from course where jsm = ?";
        ArrayList<Course>list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            //给用户对象属性赋值
            ps.setString(1, queryDao.nameQuery(username));
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setCourseNum(rs.getString("kh"));
                course.setCourseName(rs.getString("km"));
                course.setTeacherNum(rs.getInt("jsh"));
                course.setCourseCredit(rs.getInt("xf"));
                course.setCourseTime(rs.getString("sj"));
                course.setCoursePlace(rs.getString("dd"));
                list.add(course);
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return list;
    }

    //根据课号和学号确定教师号
    public static int teaNumQuery(String courseNum,String studentNum){
        Connection conn = DBUtils.getConnection();
        int res = 0;
        try{
            String sql = "select * from selectcourse where xh = ? and kh = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            //给用户对象属性赋值
            ps.setString(1, studentNum);
            ps.setString(2,courseNum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                res = rs.getInt("jsh");
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return res;
    }

    //根据课号和教师号确定课名和学分
    public static Course courseQuery(String courseNum,int teacherNum){
        Connection conn = DBUtils.getConnection();
        Course course = new Course();
        try{
            String sql = "select * from course where kh = ? and jsh = ?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            //给用户对象属性赋值
            ps.setString(1, courseNum);
            ps.setInt(2,teacherNum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                course.setCourseName(rs.getString("km"));
                course.setCourseCredit(rs.getInt("xf"));
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn);
        }
        return course;
    }
    //测试
//    public static void main(String[] args) {
//        ArrayList<courseGrade> list = queryDao.teaGradeQuery("100001");
//            for(courseGrade x:list){
//                System.out.println(x.getCourseNum() + x.getCourseName() +
//                        x.getStudentNum() + x.getStudentName() + x.getCourseGrade());
//            }
//    }
}

