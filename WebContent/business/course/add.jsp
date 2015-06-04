<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form action="../business/course/addProcess.action" method="post"
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
		<label><span style="color: red">*</span>上课教师</label> <span
			class="field"> <s:select list="teacherList" id="teacher"
				listKey="id" listValue="name" cssStyle="width:120px;"
				name="course.teacher.id" cssClass="positionSelect"
				emptyOption="true"></s:select> <span class="help-inline"
			style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>所属专业</label> <span
			class="field"> <s:select list="specialtyList" id="specialty"
				listKey="id" listValue="name" cssStyle="width:170px;"
				name="course.specialty.id" cssClass="positionSelect"
				emptyOption="true"></s:select> <span class="help-inline"
			style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>上课周数</label> <span
			class="field"> <s:select list="zhouciList" id="zhouciList"
				listKey="masterid" listValue="mastername" cssStyle="width:90px;"
				name="course.zhouci.masterid" cssClass="positionSelect"
				emptyOption="true"></s:select> <span class="help-inline"
			style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>上课节数</label> <span
			class="field"> <s:select list="jieshuList" id="jieshuList"
				listKey="masterid" listValue="mastername" cssStyle="width:100px;"
				name="course.jieshu.masterid" cssClass="positionSelect" emptyOption="true"></s:select>
			<span class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>上课位置</label> <span
			class="field"> <s:select list="weizhiList" id="weizhiList"
				listKey="masterid" listValue="mastername" cssStyle="width:140px;"
				name="course.weizhi.masterid" cssClass="positionSelect" emptyOption="true"></s:select>
			<span class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>上课最大人数</label> <span
			class="field"> <s:textfield id="allStuedentCount" maxlength="4"
				name="course.allStuedentCount" cssStyle="width:70px;"
				cssClass="text-input small-input"></s:textfield> &nbsp;&nbsp; 人<span
			class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="add_b">添加</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
</form>

