<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table id="scorelist" class="stdtable stdtablecb" cellspacing="0"
	cellpadding="0" border="0"
	style="padding-bottom: 0px; padding-left: 20px; padding-right: 20px; padding-top: 0px;">
	<thead>
		<tr>
			<th width="1%">序号</th>
			<th width="18%">学科类型</th>
			<th width="5%">成绩</th>
			<th width="5%">年份</th>
			<th width="5%">学期</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="DATALIST" status="st">
			<tr>
				<td style="border-left: 1px solid #DDD;"><s:property value="#st.count+(currentPage-1)*10" /></td>
				<td><s:property value="type.name" /></td>
				<td>
					<s:property value="score"></s:property>
				</td>
				<td><s:property value="year_str" /></td>
				<td><s:property value="type.term.mastername" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<div class="dataTables_info" id="dyntable2_info"
	style="margin-left: 20px; margin-right: 20px;">
	<span class="txt-smaller txt-light"> 当前共有<span class="number"
		style="color: red;"><s:property value="resultCount" /> </span>条成绩信息
	</span>
</div>
<div class="dataTables_paginate paging_full_numbers"
	style="margin-left: 20px; margin-right: 20px;">
	<jsp:include page="/template/page.jsp"></jsp:include>
</div>
