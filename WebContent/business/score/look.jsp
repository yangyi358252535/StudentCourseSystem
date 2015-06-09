<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form style="margin-left: 20px; margin-right: 20px;" class="stdform stdform2"
	id="addForm">
	<ul class="buttonlist">
		<li style=" padding-left: 50px;">学号&nbsp;&nbsp;
		<s:textfield   name="student.num" disabled="true"
			cssClass="input-xlarge focused" cssStyle="width:100px;" /> &nbsp;</li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li style="margin-left: 15px;">学生姓名 &nbsp;&nbsp;<s:textfield id="num" name="student.name" disabled="true" cssStyle="width:100px;"
						cssClass="input-small focused" /></li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li style="margin-left: 34px;">电话号码 &nbsp;&nbsp;<s:textfield id="storeInNum" disabled="true" name="student.tel" cssStyle="width:100px;" /></li>
		
	</ul>
	<ul class="buttonlist" style="border-bottom: 1px solid #DDD;">
		<li style=" padding-left: 23px;">注册时间&nbsp;&nbsp;&nbsp;
			<s:textfield  name="student.createDate"
				cssStyle="width:100px;" disabled="true" />
		</li>
		<li style="margin-left: 43px;">所属学院&nbsp;&nbsp;&nbsp;
			<s:textfield  name="student.clasz.specialty.name"
				cssStyle="width:100px;" disabled="true" />
		</li>
		<li style="margin-left: 57px;">所属专业&nbsp;&nbsp;&nbsp;
			<s:textfield  name="student.clasz.specialty.name"
				cssStyle="width:100px;" disabled="true" />
		</li>
		<li style="margin-left: 53px;">所属班级&nbsp;&nbsp;&nbsp;
			<s:textfield  name="student.clasz.name"
				cssStyle="width:100px;" disabled="true" />
		</li>
	</ul>
	<div class="contenttitle2" style="margin-left: 20px;">
		<h3 style="margin-top: -10px; margin-left: -10px;">
			<span class="help-inline">成绩信息列表列表</span> 
		</h3>
	</div>
	<div class="box-content" >
		<table id="scoreList" class="stdtable stdtablecb" cellspacing="0"
			cellpadding="0" border="0">
			<thead>
				<tr>
					<th width="1%">序号</th>
					<th width="18%">学科类型</th>
					<th width="5%">成绩</th>
					<th width="5%">年份</th>
					<th width="5%">学期</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<s:iterator value="scoreList" status="st">
							<tr>
								<s:hidden name="id"></s:hidden>
								<td style="border-left: 1px solid #DDD;"><s:property value="#st.count+(currentPage-1)*10" /></td>
								<td><s:property value="type.name" /></td>
								<td>
									<s:property value="score" />
								</td>
								<td><s:property value="year_str" /></td>
								<td><s:property value="term.mark" /></td>
							</tr>
						</s:iterator>
			</tbody>
		</table>
	</div>
	<p class="stdformbutton">
		<button class="stdbtn btn_black" type="button" id="cancel_b">取消</button>
	</p>
</form>