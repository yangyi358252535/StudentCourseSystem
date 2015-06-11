<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../myinfo/score/js/score_main.js"
	charset="UTF-8"></script>
<div class="contenttitle2" style="margin-left: 20px;">
	<h3 id="userTitle">我的成绩信息列表</h3>
</div>
<div id="scorebar"
	style="padding-bottom: 0px; padding-left: 20px; padding-right: 20px; padding-top: 10px;">
	<div class="tableoptions">
		&nbsp;&nbsp;&nbsp;&nbsp;查询:&nbsp; <select id="condition1" tabindex="1"
			style="width: 130px;">
			<option value="0">全部成绩</option>
			<option value="1">按课程名称查询</option>
			<option value="2">按学年查询</option>
		</select> &nbsp;&nbsp;<select id="condition2" tabindex="2"
			style="width: 60px;" disabled="disabled">
		</select>  <input type="text" size="20px" id="input"
			class="inputTextCssStyle" style="width: 130px;"> &nbsp;&nbsp;
			
		<a class="btn btn2 btn_search" href="#" id="search_but"><span>搜索</span>
		</a>
	</div>
</div>
<div id="score_main"></div>
