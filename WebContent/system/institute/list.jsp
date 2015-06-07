<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table id="institutelist" class="stdtable stdtablecb" cellspacing="0"
	cellpadding="0" border="0"
	style="padding-bottom: 0px; padding-left: 20px; padding-right: 20px; padding-top: 0px;">
	<thead>
		<tr>
			<th width="1%"><s:checkbox id="selectAll" name="all" /></th>
			<th width="2%">序号</th>
			<th width="38%">学院名称</th>
			<th width="10%">添加时间</th>
			<th width="5%">删除标记</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="DATALIST" status="st">
			<tr>
				<s:hidden name="id"></s:hidden>
				<td style="border-left: 1px solid #ddd;"><s:checkbox name="sel" />
				</td>
				<td><s:property value="#st.count+(currentPage-1)*10" /></td>
				<td><s:property value="name" /></td>
				<td><s:property value="createDate" /></td>
				<td><s:if test="deleteflag==0">使用中</s:if> 
					<s:if test="deleteflag==1">已删除</s:if> 
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<div class="dataTables_info" id="dyntable2_info"
	style="margin-left: 20px; margin-right: 20px;">
	<span class="txt-smaller txt-light"> 当前共有<span class="number"
		style="color: red;"><s:property value="resultCount" /> </span>个学院信息
	</span>
</div>
<div class="dataTables_paginate paging_full_numbers"
	style="margin-left: 20px; margin-right: 20px;">
	<jsp:include page="/template/page.jsp"></jsp:include>
</div>
