package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.operateDao;
import Dao.userDao;
import bean.User;
import com.mysql.cj.Session;

@WebServlet("/selectServlet")
public class selectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String courseNum = request.getParameter("courseNum");
        String teacherNum = request.getParameter("teacherNum");
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();
        if(operateDao.selectSuccess(courseNum, teacherNum, username)){//成功
            //  level = user.getLevel();
            request.getRequestDispatcher("stuCourseQuery.jsp").forward(request, response);
        }else {//失败
            request.setAttribute("info"," 该课程不存在或已经选择此课程！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

}

