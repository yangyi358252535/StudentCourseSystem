<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<head>
<script type="text/javascript" src="../homePage/js/configCustom.js" charset="UTF-8"></script>
</head>
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" id="profileClose">×</button>
		<h3>账户信息</h3>
	</div>
	<div class="modal-body" style="height:120px;max-height: 120px;overflow-y: hidden;">
		<div style="width:250px;"><img src="../img/1.png" width="50px" style="position:relative;left:50px;top:20px;width: 80px;">
			<div style="position: relative;left:170px;bottom:60px;font-size: 16px;">
				<div style="padding-bottom: 5px;">账号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${currentUser.num}</div>
				<div style="padding-bottom: 5px;">姓名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${currentUser.name}</div>
				<div style="padding-bottom: 5px;">工龄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1年</div>
				<div>权限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${currentUserAuth}</div>
			</div>
		</div>
	</div>
	<div class="modal-header">
		<h3>安全管理</h3>
	</div>
	<div class="modal-body" style="height:115px;max-height: 115px;overflow: hidden;">
		<div style="position:relative; left:120px;">
			<form action="" method="post" id="modifyPass">
			<fieldset>
			<div class="control-group" style="margin-bottom: 5px;">
				<label class="control-label" for="oldPass">
					旧密码&nbsp;&nbsp;&nbsp;&nbsp;<s:password name="oldPass" id="oldPass" cssClass="popupInputText"/>	
					&nbsp;&nbsp;<span class="help-inline" id="old" style="color:red;"></span>
				</label>
			</div>
			<div class="control-group" style="margin-bottom: 5px;">
				<label class="control-label" for="newPass">
					新密码&nbsp;&nbsp;&nbsp;&nbsp;<s:password name="newPass" id="newPass" cssClass="popupInputText"/>	
					&nbsp;&nbsp;<span class="help-inline" style="color:red;"></span>
				</label>
			</div>
			<div class="control-group" style="position:relative; left:-48px;">
				<label class="control-label" for="newPassAgain">
					再次输入新密码&nbsp;&nbsp;&nbsp;&nbsp;<s:password name="newPassAgain" id="newPassAgain" cssClass="popupInputText"/>	
					&nbsp;&nbsp;<span class="help-inline" style="color:red;"></span>
				</label>
			</div>
			</fieldset>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<button class="stdbtn btn_black" data-dismiss="modal" id="cancel">关闭</button>
		<button class="stdbtn btn_blue" id="edit_b_con">保存修改</button>
	</div>