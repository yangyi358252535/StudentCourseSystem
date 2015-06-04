<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="../business/mylecture/js/mylecture_main.js" charset="UTF-8"></script>
<div class="contenttitle2" style="margin-left: 20px;">
	<h3 id="userTitle">我的课程信息列表</h3>
</div>
<div id="mylecturebar"
	style="padding-bottom: 0px; padding-left: 20px; padding-right: 20px; padding-top: 10px;">
	<div class="tableoptions">
		&nbsp;&nbsp;&nbsp;&nbsp;查询:&nbsp; <select id="condition1" tabindex="1"
			style="width: 130px;">
			<option value="0">全部课程</option>
			<option value="1">按课程名称查询</option>
			<option value="2">按教师姓名查询</option>
			<option value="3">按通过状态查询</option>
		</select> &nbsp;&nbsp; <select id="condition2" tabindex="2"
			style="width: 100px;" disabled="disabled">
		</select>&nbsp;&nbsp; <input type="text" size="20px" id="input"
			class="inputTextCssStyle" style="width: 130px;"> &nbsp;&nbsp;
		<a class="btn btn2 btn_search" href="#" id="search_but"><span>搜索</span>
		</a> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; <a
			class="btn btn2 btn_yellow btn_inboxi" href="#" id="add_but"><span>评分</span>
		</a> &nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn2 btn_black btn_search"
			href="#" id="look_but"><span>查看</span> </a>
	</div>
</div>
<div id="mylecture_main"></div>
