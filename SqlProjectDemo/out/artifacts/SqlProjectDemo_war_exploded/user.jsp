<%@ page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
	<style>
		body {
			background-image: url(images/bg.jpg);
			background-position: center center;
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: cover;
		}
	</style>
	<meta charset="UTF-8">
	<title>用户操作界面</title>
	<link rel="stylesheet" type="text/css" href="../css/user&admin.css">
	<link rel="icon" type="image/x-ico" href="images/stu.ico">
	<link rel="stylesheet" type="text/css" href="../css/form.css">
</head>
	
<body>
	<%
		//获取登录成功的用户信息
		User user = (User) session.getAttribute("user");
		//判断用户是否登录
		if(user != null){
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
		<%
		}else{
			response.sendRedirect("login.html");
		}
		%>
		<div class="container">
			<div class="select">
				<h3>请选择操作</h3>
				<ul id="accordion" class="accordion">
					<li><div class="link"><a href="selectCourse.jsp">选课</a></div></li>
					<li><div class="link"><a href="login.html">退课</a></div></li>
					<li><div class="link"><a href="stuCourseQuery.jsp">我的课程</a></div></li>
					<li><div class="link"><a href="stuGradeQuery.jsp">成绩查询</a></div></li>
				</ul>
				</div>

				<div id="result" class="result">
					<p class="welcome">欢迎使用学生信息管理系统！</p>
				</div>
			</div>
		</div>
	</main>
	<div class="MainContainer">
		<div  id="main" class="main">
			内容
		</div>
	</div>
	<footer>
		<div class="copyright">
			&copy; Copyright. All rights reserved. Design by <a href="http://www.github.EliminateDesire/EliminateDesire/">qiyuqiyu.</a>
		</div>
	</footer>
</body>
</html>