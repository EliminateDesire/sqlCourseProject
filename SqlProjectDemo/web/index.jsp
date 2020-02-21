<%--
  Created by IntelliJ IDEA.
  User: gg
  Date: 2020/2/20
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.queryDao" %>

<html>
  <head>
    <title>成绩查询</title>
    <link rel="stylesheet" type="text/css" href="css/user&admin.css">
    <link rel="icon" type="image/x-ico" href="images/stu.ico">
  </head>
  <body>
    <%
      User user = (User) session.getAttribute("user");
    %>
    <main>
      <div class="container">
        <div id="result" class="result">
            <table>
              <tr>
                <li><td>课程号</td><td>课程名</td><td>教师</td><td>学分</td><td>成绩</td></li>
              </tr>
            </table>
              <%
                ArrayList<?>list = queryDao.stuGradeQuery(user.getUsername());
              %>
            </tr>
        </div>
      </div>
    </main>
  </body>
</html>
