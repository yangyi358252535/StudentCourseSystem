<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>添加学生信息
		</h2>
	</div>
	<div class="box-content">
		<form action="../appraiseManage/student/addProcess.action"
			method="post" class="form-horizontal" id="addForm">
			<div class="control-group">
				<label class="control-label" for="name"> <span
					style="color: red">*</span>学生姓名</label>
				<div class="controls">
					<s:textfield name="student.name" id="name"
						cssClass="input-mini focused" maxlength="40" />
					&nbsp;&nbsp;<span class="help-inline" style="color: red;"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="age"> <span
					style="color: red">*</span>性别</label>
				<div class="controls">
					<s:radio list="#{'男':'男','女':'女' }" value="'男'" name="student.sex"></s:radio>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="tel"><span
					style="color: red">*</span>联系电话</label>
				<div class="controls">
					<s:textfield id="tel" name="student.tel" cssClass="input-small focused" />
					&nbsp;&nbsp;<span class="help-inline" style="color: red;"></span>
				</div>
			</div>
			<div class="form-actions">
				<button type="button" class="btn btn-primary" id="add_b111">添加</button>
				<button type="button" class="btn" id="cancel_b">返回</button>
			</div>
		</form>
	</div>
</div>