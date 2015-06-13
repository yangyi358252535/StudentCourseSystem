<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript"
	src="../business/score/js/student_main.js" charset="UTF-8"></script>
<div class="contenttitle2" style="margin-left: 20px;">
	<h3 id="userTitle">学生信息列表</h3>
</div>
<div id="studentbar"
	style="padding-bottom: 0px; padding-left: 20px; padding-right: 20px; padding-top: 10px;">
	<div class="tableoptions">
		&nbsp;&nbsp;&nbsp;&nbsp;查询:&nbsp; <select id="condition1" tabindex="1"
			style="width: 130px;">
			<option value="0">全部学生信息</option>
			<option value="1">按登录账号查询</option>
			<option value="2">按姓名查询</option>
		</select>&nbsp;&nbsp; <input type="text" size="20px" id="input"
			class="inputTextCssStyle" style="width: 130px;"> &nbsp;&nbsp;
		<a class="btn btn2 btn_search" href="#" id="search_but"><span>搜索</span>
		</a> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
		<s:if test="showflag==1">
		 <a
			class="btn btn2 btn_yellow btn_inboxi" href="#" id="input_but"><span>成绩录入</span>
		</a> 
		</s:if>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn2 btn_black btn_search"
			href="#" id="look_but"><span>查看</span> </a>
		&nbsp;录入成绩年份：
		<select class="input-small focused" id="year_str">
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
			<option value="2019">2019</option>
			<option value="2020">2020</option>
			<option value="2021">2021</option>
		</select>
		&nbsp;录入成绩学期：
		<select class="input-small focused" id="term_str">
			<option value="1">上半学期</option>
			<option value="2">下半学期</option>
		</select>
	</div>
</div>
<div id="student_main"></div>