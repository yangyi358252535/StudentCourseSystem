<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form action="../system/specialty/modifyProcess.action" method="post"
	style="margin-left: 20px; margin-right: 20px;" class="stdform stdform2"
	id="editForm">
	<p>
		<label><span style="color: red">*</span>专业名称</label> <span
			class="field"> <s:textfield id="name" 
				name="specialty.name" cssStyle="width:270px;"
				cssClass="text-input small-input"></s:textfield> &nbsp;&nbsp; <span
			class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label>专业简介</label> <span class="field"> <s:textarea
				name="specialty.comment" cssStyle="width:400px;height:100px;"></s:textarea>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="edit_b">修改</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
	<s:hidden name="specialty.id"></s:hidden>
	<s:hidden name="specialty.createDate"></s:hidden>
</form>
