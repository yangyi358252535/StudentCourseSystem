<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="../appraiseManage/student/js/student_main.js" charset="UTF-8"></script>
<div id="studentbar" style="position: relative; left: 20px; top: 0px;">
	&nbsp;&nbsp;&nbsp;&nbsp;查询:&nbsp; <select id="condition1" tabindex="1"
		style="width: 160px;">
		<option value="0">全部学生信息</option>
		<option value="1">按编号查询</option>
		<option value="2">按姓名查询</option>
	</select> &nbsp;&nbsp; <input type="text" size="20px" class="focused" id="input" tabindex="3"
		style="width: 130px;"> &nbsp;&nbsp;
	<button id="search_but" class="btn btn-info btn-mini"
		style="position: relative; top: 0px;">搜索</button>
	&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
	<button class="btn btn-success" id="add_but">添加</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="btn btn-warning" id="edit_but">编辑</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="btn btn-inverse" id="award_but">奖项录入</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="btn btn-info" id="input_but">成绩录入</button>
		&nbsp;录入成绩年份：
		<select class="input-small focused" id="year_str">
			<option value="2015" >2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
			<option value="2019">2019</option>
		</select>
</div>
<div class="box-content" id="student_main"></div>
