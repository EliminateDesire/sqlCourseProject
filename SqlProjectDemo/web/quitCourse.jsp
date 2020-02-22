<%--
  Created by IntelliJ IDEA.
  User: gg
  Date: 2020/2/22
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.queryDao" %>
<%@ page import="bean.courseGrade" %>
<%@ page import="bean.Course" %>
<%@ page import="Dao.operateDao" %>

<!DOCTYPE HTML>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>用户操作界面</title>
    <link rel="stylesheet" type="text/css" href="../css/user&admin.css">
    <link rel="icon" type="image/x-ico" href="images/stu.ico">
    <link rel="stylesheet" type="text/css" href="../css/form.css">
    <style>
        body {
            background-image: url(images/bg.jpg);
            background-position: center center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
<%
    if(session == null) response.sendRedirect("login.html");
    User user = (User) session.getAttribute("user");
%>
<header>
    <div class="title">
        <span>用户操作界面</span>
    </div>
    <nav>
        <div class="userinfo">
            <ul>
                <li><%=user.getUsername() %></li>
                <%--					<li><%=user.getLevel() %></li>--%>
                <li><a href="UserExitServlet">退出登录</a></li>
                <li><a href="login.html">返回首页</a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <div class="select">
            <ul id="accordion" class="accordion">
                <li><div class="link"><a href="user.jsp">返回</a></div></li>
            </ul>
        </div>
        <div id="result" class="result">
            <div class="table-wrapper">
                <table class="fl-table">
                    <thead>
                    <tr>
                        <th>课程号</th><th>课程名</th><th>教师号</th><th>教师</th><th>学分</th>
                    </tr>
                    </thead>
                    <%
                        ArrayList<Course>list = operateDao.haveSelectQuery(user.getUsername());
                        for(Course x:list)
                        {
                    %>
                    <tr>
                        <td><%=x.getCourseNum()%></td>
                        <td><%=x.getCourseName()%></td>
                        <td><%=x.getTeacherNum()%></td>
                        <td><%=x.getTeacherName()%></td>
                        <td><%=x.getCourseCredit()%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>

            <form  class="form" action="quitServlet" method="post">
                <h3>退课</h3>
                <input type="text" autofocus="autofocus" name="courseNum" value placeholder="请输入课号" required="required">
                <input type="password" name="teacherNum" value placeholder="请输入教师号" required="required">
                <input id="submit" type="submit" name="submit" value="确定">
            </form>
        </div>
    </div>
</main>
<footer>
    <div class="copyright">
        &copy; Copyright. All rights reserved. Design by <a href="http://www.github.EliminateDesire/EliminateDesire/">qiyuqiyu.</a>
    </div>
</footer>
</body>
</html>
