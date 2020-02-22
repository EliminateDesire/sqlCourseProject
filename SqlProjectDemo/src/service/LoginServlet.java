package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.userDao;
import bean.User;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String level = null;
        //实例化UserDao对象
        userDao userDao = new userDao();
        User user = userDao.login(username, password);
        //判断是否登录成功
        if(user != null){//成功
                request.getSession().setAttribute("user", user);//将用户对象放到session中

             if(user.getUsername().length() == 8)
                 //学生登录
                request.getRequestDispatcher("user.jsp").forward(request, response);
             else {
                 //管理员登录
                 request.getRequestDispatcher("admin.jsp").forward(request, response);
             }

        }else {//失败
            request.setAttribute("info"," 错误:用户名或密码错误！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

}

