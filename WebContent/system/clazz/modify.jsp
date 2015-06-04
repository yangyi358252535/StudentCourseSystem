<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form action="../system/clazz/modifyProcess.action" method="post"
	style="margin-left: 20px; margin-right: 20px;" class="stdform stdform2"
	id="editForm">
	<p>
		<label><span style="color: red">*</span>班级名称</label> <span
			class="field"> <s:textfield id="name" 
				name="clasz.name" cssStyle="width:270px;"
				cssClass="text-input small-input"></s:textfield> &nbsp;&nbsp; <span
			class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>所属专业</label> <span
			class="field"> <s:select list="specialtyList" id="pl"
				listKey="id" listValue="name" cssStyle="width:250px;"
				name="clasz.specialty.id" cssClass="positionSelect"
				emptyOption="true"></s:select> <span class="help-inline"
			style="color: red;"> </span>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="edit_b">修改</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
	<s:hidden name="clasz.id"></s:hidden>
</form>
