<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-align-justify"></i><span class="break"></span>学生信息列表
		</h2>
	</div>
	<div class="box-content">
		<table id="studentlist"
			class="table table-bordered table-striped table-condensed" align="center">
			<thead>
				<tr>
					<th><s:checkbox id="selectAll" name="all" /></th>
					<th>序号</th>
					<th>学生编号</th>
					<th>姓名</th>
					<th>性别</th>
					<!--  <th>班级</th>-->
					<th>联系电话</th>
					<th>注册时间</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="DATALIST" status="st">
					<tr>
						<s:hidden name="id"></s:hidden>
						<td><s:checkbox name="sel" /></td>
						<td><s:property value="#st.count+(currentPage-1)*10" /></td>
						<td><s:property value="num" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="sex" /></td>
						<!-- <td><s:property value="clasz.name" /></td>-->
						<td><s:property value="tel" /></td>
						<td><s:property value="createDate" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="pagination pagination-centered">
			<jsp:include page="/template/page.jsp"></jsp:include>
			当前共有<span class="number" style="color: red;"><s:property
					value="resultCount" /> </span>位学生信息
		</div>
	</div>
</div>
