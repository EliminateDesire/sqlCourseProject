package Dao;

import utils.DBUtils;
import bean.courseGrade;
import bean.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class queryDao {
    //查询成绩
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

    //查询所选课程
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

    //测试
//    public static void main(String[] args) {
//        ArrayList<courseGrade> list = queryDao.stuGradeQuery("17121439");
//            for(courseGrade x:list){
//                System.out.println(x.getCourseNum() + x.getCourseName() +
//                        x.getTeacher() + x.getCourseCredit() + x.getCourseGrade());
//            }
//    }
}

