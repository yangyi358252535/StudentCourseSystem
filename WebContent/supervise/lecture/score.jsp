<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form action="../supervise/lecture/scoreProcess.action" method="post"
	style="margin-left: 20px; margin-right: 20px;"
	class="stdform stdform2" id="addForm">
	<p>
		<label>课程名称</label> <span class="field"> <s:property
				value="lecture.course.name" />
		</span>
	</p>
	<p>
		<label>教师姓名</label> <span class="field"> <s:property
				value="lecture.course.teacher.name" />
		</span>
	</p>
	<p>
		<label>上课周数</label> <span class="field"> <s:property
				value="lecture.course.zhouci.mastername" />
		</span>
	</p>
	<p>
		<label>上课节数</label> <span class="field"> <s:property
				value="lecture.course.jieshu.mastername" />
		</span>
	</p>
	<p>
		<label>上课位置</label> <span class="field"> <s:property
				value="lecture.course.weizhi.mastername" />
		</span>
	</p>
	<p>
		<label>听课评分</label>
		 <span class="field">
			<s:textfield id="allStuedentCount" maxlength="4"
				name="lecture.score" cssStyle="width:80px;"
				cssClass="text-input small-input"></s:textfield> &nbsp;&nbsp;
				 <span class="help-inline" style="color: red;"></span>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="add_b">提交评分</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
	<s:hidden name="lecture.id"></s:hidden>
</form>
