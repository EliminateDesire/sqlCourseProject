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
            // if(level.equals("用户")){
//            request.getSession().setAttribute("user", user);//将用户对象放到session中
//            //转发到user.jsp中
            request.getRequestDispatcher("selectCourse.jsp").forward(request, response);
//            }
//            else{
//                request.getSession().setAttribute("admin", user);//将管理员对象放到session中
//                //转发到admin.jsp中
//                request.getRequestDispatcher("admin.jsp").forward(request, response);
//            }
        }else {//失败
//            request.setAttribute("info"," 错误:用户名或密码错误！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

}

