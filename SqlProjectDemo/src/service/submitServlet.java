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

@WebServlet("/submitServlet")
public class submitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = (User) request.getSession().getAttribute("user");
        String courseNum = request.getParameter("courseNum");
        String studentNum = request.getParameter("studentNum");
        int courseGrade = Integer.valueOf(request.getParameter("courseGrade")).intValue();
        if(operateDao.submitSuccess(courseNum,studentNum,courseGrade,user.getUsername())){//成功
            //  level = user.getLevel();
            request.getRequestDispatcher("teaGradeSubmit.jsp").forward(request, response);
        }else {//失败
            request.setAttribute("info"," 上传失败！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }
}

