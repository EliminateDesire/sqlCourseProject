<%--
  Created by IntelliJ IDEA.
  User: gg
  Date: 2020/2/21
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.queryDao" %>
<%@ page import="bean.courseGrade" %>
<%@ page import="bean.Course" %>

<!DOCTYPE HTML>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>用户操作界面</title>
    <link rel="stylesheet" type="text/css" href="../css/user&admin.css">
    <link rel="icon" type="image/x-ico" href="../images/stu.ico">
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
                        <th>课程名</th><th>教师</th><th>学分</th><th>时间</th><th>地点</th>
                    </tr>
                    </thead>
                    <%
                        ArrayList<Course>list = queryDao.stuCourseQuery(user.getUsername());
                        for(Course x:list)
                        {
                    %>
                    <tr>
                        <td><%=x.getCourseName()%></td>
                        <td><%=x.getTeacherName()%></td>
                        <td><%=x.getCourseCredit()%></td>
                        <td><%=x.getCourseTime()%></td>
                        <td><%=x.getCoursePlace()%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
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
