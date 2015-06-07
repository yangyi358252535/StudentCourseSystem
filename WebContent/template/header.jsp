<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="topheader">
	<div class="left">
		<% if((Integer)session.getAttribute("currentUserCode")==0) {%>
			<h1 class="logo">
			学生成绩管理系统(管理员端)<span></span>
			</h1>
		<% }else if((Integer)session.getAttribute("currentUserCode")==1){%>
			<h1 class="logo">
			学生成绩管理系统(教师端)<span></span>
			</h1>
		<% }else if((Integer)session.getAttribute("currentUserCode")==2){%>
			<h1 class="logo">
			学生成绩管理系统(督教端)<span></span>
			</h1>
		<% } %>		
		<br clear="all" />
	</div>
	<div class="right">
		<div class="userinfo">
			<img src="images/thumbs/avatar.png" alt="" /> <span>${currentUser.name}</span>
		</div>
		<div class="userinfodrop">
			<div class="avatar">
				<a href="#"><img src="images/thumbs/avatarbig.png" alt="" />
				</a>
			</div>
			<div class="userdata">
				<h4>${currentUser.name}</h4>
				<span class="email">youremail@yourdomain.com</span>
				<ul>
					<!-- <li><a href="#" id="profile">账户设置</a>
					</li> -->
					<li><a href="#" id="logout">注销</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
