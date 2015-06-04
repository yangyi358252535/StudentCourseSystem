<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>欢迎使用学生成绩管理系统</title>
<link rel="stylesheet" href="homePage/css/style.default.css"
	type="text/css" />
<script type="text/javascript"
	src="homePage/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="homePage/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript"
	src="homePage/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript"
	src="homePage/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="homePage/js/custom/general.js"></script>
<script type="text/javascript" src="homePage/js/custom/index.js"></script>
<script type="text/javascript" src="homePage/js/login.js"></script>
</head>
<body class="loginpage">
	<div class="loginbox">
		<div class="loginboxinner">
			<div class="logo">
				<h1>
					<span>学生成绩管理</span>登录
				</h1>
			</div>
			<!--logo-->
			<span class="loginmsg" id="login_inf" style="display: none;color:red;"></span>
			<form id="login" action="#">
				<div class="username">
					<div class="usernameinner">
						<input type="text" name="username" id="username" title="请输入用户名"
							value="admin" />
					</div>
				</div>
				<div class="password">
					<div class="passwordinner">
						<input type="password" name="password" id="password" title="请输入密码"
							value="admin" />
					</div>
				</div>
				<div class="subcontent">
					<p>
						<label style="color: white;">角色</label>
						<span class="field"> 
							<select name="select" id="role">
									<option value="0">管理员登录</option>
									<option value="1">学生登录</option>
									<option value="2">教师登录</option>
							</select>
						</span>
					</p>
				</div>
				<div><p> &nbsp;</p></div>
				<button type="button" id="login_but1">登录</button>
			</form>
		</div>
	</div>
</body>
</html>
