<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form action="../system/course/addProcess.action" method="post"
	style="margin-left: 20px; margin-right: 20px;" class="stdform stdform2"
	id="addForm">
	<p>
		<label><span style="color: red">*</span>课程名称</label> <span
			class="field"> <s:textfield id="name"  
				name="course.name" cssStyle="width:270px;"
				cssClass="text-input small-input"></s:textfield> &nbsp;&nbsp; <span
			class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>任课教师</label> <span
			class="field"> <s:select list="teacherList" id="teacherpl"
				listKey="id" listValue="name" cssStyle="width:270px;"
				name="course.teacher.id" cssClass="positionSelect"
				emptyOption="true"></s:select> <span class="help-inline"
			style="color: red;"> </span>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="add_b">添加</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
</form>