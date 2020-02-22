package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.operateDao;
import Dao.queryDao;
import Dao.userDao;
import bean.User;
import com.mysql.cj.Session;

@WebServlet("/openServlet")
public class openServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String courseNum = request.getParameter("courseNum");
        String courseName = request.getParameter("courseName");
        int teacherNum = Integer.valueOf(request.getParameter("teacherNum")).intValue();
        User user = (User) request.getSession().getAttribute("user");
        String teacherName = queryDao.nameQuery(user.getUsername());
        int courseCredit = Integer.valueOf(request.getParameter("courseCredit")).intValue();
        String courseTime = request.getParameter("courseTime");
        String coursePlace = request.getParameter("coursePlace");
        if(operateDao.openSuccess(courseNum,courseName,teacherNum,teacherName,courseCredit,courseTime,coursePlace)){//成功
            //  level = user.getLevel();
            request.getRequestDispatcher("teaOpenCourse.jsp").forward(request, response);
        }else {//失败
            request.setAttribute("info"," 开课失败！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }
}

