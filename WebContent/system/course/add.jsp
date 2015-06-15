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
		<label><span style="color: red">*</span>所属专业</label> <span
			class="field"> <s:select list="specialtyList" id="pl"
				listKey="id" listValue="showName" cssStyle="width:270px;"
				name="course.specialty.id" cssClass="positionSelect"
				emptyOption="true"></s:select> <span class="help-inline"
			style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>所属学年</label> <span
			class="field"> <s:select list="#{'2015':'2015'
											,'2016':'2016'
											,'2017':'2017'
											,'2018':'2018'
											,'2019':'2019'
											,'2020':'2020'
											,'2021':'2021' }" id="xn"
				listKey="key" listValue="value" cssStyle="width:120px;"
				name="course.year_str" cssClass="positionSelect" ></s:select> 
				<span class="help-inline"
			style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>所属学期</label> <span
			class="field">
			<s:select list="masterList" id="xq"
				listKey="masterid" listValue="mastername" cssStyle="width:150px;"
				name="course.term.masterid" cssClass="positionSelect" ></s:select>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="add_b">添加</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
</form>