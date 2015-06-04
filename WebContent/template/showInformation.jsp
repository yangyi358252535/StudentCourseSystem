<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//校验提示框
%>
<div id="effect" style="display: none"
	class="ui-state-highlight ui-corner-all"></div>

<div id="medicineWarnning" style="display: none"></div>

<%
	//提示框
%>
<div id="dialog-message" title="提示">
	<div style="position: absolute; left: 25px; top: 22px;">
		<img id="img1" src="../img/msginfo.png" style="display: none">
	</div>
	<div style="position: absolute; left: 45px; top: 22px; color: red;"
		id="alertInfo"></div>
</div>
<%
	//loading显示
%>
<div class="overlay"></div>
<div id="loading"
	style="display: none; position: fixed; top: 0; left: 50%; z-index: 9999; opacity: 1; filter: alpha(opacity =1); margin-left: -80px; margin-top: -2500000px;"
	class="center">
	Loading...
	<div class="center"></div>
</div>
<%
	//confirm框
%>
<div id="dialog-confirm" title="请选择">
	<div style="position: absolute; left: 25px; top: 22px;">
		<img id="img2" src="../img/msginfo.png" style="display: none">
	</div>
	<div style="position: absolute; left: 45px; top: 22px; color: red;"
		id="confirmInfo"></div>
</div>
<%
	//信息添加成功信息显示
%>
<div class="ui-widget" style="width: 320px; display: none"
	id="successInfo">
	<div class="notibar msginfo">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin-right: .3em; margin-top: .8em;"></span> <strong
				style="position: relative; color: red;"></strong>
		</p>
	</div>
</div>
	<div class="modal hide fade" id="myModal">
		</div>