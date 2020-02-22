<%--
  Created by IntelliJ IDEA.
  User: gg
  Date: 2020/2/22
  Time: 21:14
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
        <span>上传成绩界面</span>
    </div>
    <nav>
        <div class="userinfo">
            <ul>
                <li><%=user.getUsername() %></li>
                <li><%=user.getLevel() %></li>
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
                <li><div class="link"><a href="admin.jsp">返回</a></div></li>
            </ul>
        </div>
        <div id="result" class="result">
            <table class="fl-table">
                <thead>
                <tr>
                    <th>学号</th><th>姓名</th><th>课程号</th><th>课程名</th><th>分数</th>
                </tr>
                </thead>
                <%
                    ArrayList<courseGrade>list = queryDao.teaGradeQuery(user.getUsername());
                    for(courseGrade x:list)
                    {
                %>
                <tr>
                    <td><%=x.getStudentNum()%></td>
                    <td><%=x.getStudentName()%></td>
                    <td><%=x.getCourseNum()%></td>
                    <td><%=x.getCourseName()%></td>
                    <td><%=x.getCourseGrade()%></td>
                </tr>
                <%
                    }
                %>
            </table>

            <form  class="form" action="submitServlet" method="post">
                <h3>上传成绩</h3>
                <input type="text" name="courseNum" value placeholder="请输入课程号" required="required">
                <input type="text" name="studentNum" value placeholder="请输入学号" required="required">
                <input type="text" name="courseGrade" value placeholder="请输入总评成绩" required="required">
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