<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form action="../system/institute/modifyProcess.action" method="post"
	style="margin-left: 20px; margin-right: 20px;" class="stdform stdform2"
	id="editForm">
	<p>
		<label>创建时间</label> <span
			class="field"> <s:property value="institute.createDate"/>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>学院名称</label> <span
			class="field"> <s:textfield id="name" 
				name="institute.name" cssStyle="width:370px;"
				cssClass="text-input small-input"></s:textfield> &nbsp;&nbsp; <span
			class="help-inline" style="color: red;"> </span>
		</span>
	</p>
	<p>
		<label><span style="color: red">*</span>删除标记</label> <span
			class="field"> <s:radio list="#{0:'使用',1:'禁用'}" name="institute.deleteflag"></s:radio>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_blue" type="button" id="edit_b">修改</button>
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
	<s:hidden name="institute.id"></s:hidden>
	<s:hidden name="institute.createDate"></s:hidden>
</form>
