<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form action="../business/student/addProcess.action" method="post"
	style="margin-left: 20px; margin-right: 20px;" class="stdform stdform2"
	id="addForm">
	<p>
		<label><span style="color: red">*</span>姓名</label> <span class="field">
			<s:textfield id="name" maxlength="4" name="student.name"
				cssStyle="width:70px;" cssClass="text-input small-input"></s:textfield>
			&nbsp;&nbsp; <span class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label>性别</label><span class="field"> <span class="checked"><input
				name="student.sex" id="optionsRadios1" value="男" type="radio"
				checked="checked">男</span> <span><input name="student.sex"
				id="optionsRadios2" value="女" type="radio">女</span> </span>
	</p>
	<p>
		<label><span style="color: red">*</span>年龄</label> <span class="field">
			<s:textfield id="age" maxlength="4" name="student.age"
				cssStyle="width:70px;" cssClass="text-input small-input"></s:textfield>
			&nbsp;&nbsp; <span class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>联系电话</label> <span
			class="field"> <s:textfield id="tel"  
				name="student.tel" cssStyle="width:90px;"
				cssClass="text-input small-input"></s:textfield> &nbsp;&nbsp; <span
			class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>所属班级</label> <span
			class="field"> <s:select list="claszList" id="pl" listKey="id"
				listValue="name" cssStyle="width:150px;" name="student.class1.id"
				cssClass="positionSelect" emptyOption="true"></s:select> <span
			class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="add_b">添加</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
</form>
