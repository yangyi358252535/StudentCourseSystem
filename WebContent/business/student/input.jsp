<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>录入成绩
		</h2>
	</div>
	<div class="box-content">
		<form class="form-horizontal" action="../appraiseManage/student/scoreProcess.action"
					method="post"  id="addForm">
			<div class="row-fluid"
				style="padding-bottom: 10px; padding-top: 10px;">
				<div></div>
				<div class="span3">
					&nbsp;&nbsp;&nbsp;学生编号&nbsp;&nbsp;
					<s:textfield id="num" name="student.num" disabled="true"
						cssClass="input-small focused" />
				</div>
				<div class="span3">
					&nbsp;学生姓名 &nbsp;&nbsp;
					<s:textfield id="readernum" name="student.name"
						cssClass="input-mini focused" disabled="true" />
				</div>
			</div>
			<div class="row-fluid" style="padding-bottom: 5px;">
				<div></div>
				<div class="span3">
					&nbsp; 电话号码 &nbsp;&nbsp;
					<s:textfield id="tel" name="student.tel"
						cssClass="input-small focused" disabled="true" />
				</div>
				<div class="span3">
					&nbsp; 注册时间&nbsp;&nbsp;
					<s:textfield id="createDate" name="student.createDate"
						cssClass="input-medium focused" disabled="true" />
				</div>
			</div>
			<div class="box-header">
				<h2>
					<i class="icon-align-justify"></i><span class="break"></span>科目成绩录入列表
				</h2>
			</div>
			<div class="" style="overflow-y: auto; max-height: 320px;padding-top: 10px;padding-left: 0px;padding-right: 0px;">
				<table id="scoreList"
					class="table table-bordered table-striped table-condensed">
					<thead>
						<tr>
							<th width="1%">序号</th>
							<th width="18%">学科类型</th>
							<th width="5%">成绩</th>
							<th width="5%">评优积分</th>
							<th width="5%">年份</th>
							<th width="5%">学期</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="scoreList" status="st">
							<tr>
								<s:hidden name="id"></s:hidden>
								<td><s:property value="#st.count+(currentPage-1)*10" /></td>
								<td><s:property value="type.mastername" /></td>
								<td>
									<s:textfield name="score" cssClass="input-mini focused" cssStyle="text-align:right;font-weight:bold;float:right;"></s:textfield>
								</td>
								<td><s:property value="integration" /></td>
								<td><s:property value="year_str" /></td>
								<td><s:property value="term.mastername" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="form-actions">
				<button type="button" class="btn btn-primary" id="edit_b">提交</button>
				<button type="button" class="btn" id="cancel_b">返回</button>
			</div>
		</form>
	</div>
</div>

