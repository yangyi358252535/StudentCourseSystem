<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table id="mylecturelist" class="stdtable stdtablecb" cellspacing="0"
	cellpadding="0" border="0"
	style="padding-bottom: 0px; padding-left: 20px; padding-right: 20px; padding-top: 0px;">
	<thead>
		<tr>
			<th width="1%"><s:checkbox id="selectAll" name="all" /></th>
			<th width="4%">序号</th>
			<th width="18%">课程名称</th>
			<th width="18%">添加时间</th>
			<th width="8%">周几</th>
			<th width="8%">节数</th>
			<th width="8%">上课教室</th>
			<th width="8%">听课评分</th>
			<th width="8%">评分状态</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="DATALIST" status="st">
			<tr>
				<s:hidden name="id"></s:hidden>
				<td style="border-left: 1px solid #ddd;"><s:checkbox name="sel" />
				</td>
				<td><s:property value="#st.count+(currentPage-1)*10" /></td>
				<td><s:property value="course.name" /></td>
				<td><s:property value="createDate" /></td>
				<td><s:property value="course.zhouci.mastername" /></td>
				<td><s:property value="course.jieshu.mastername" /></td>
				<td><s:property value="course.weizhi.mastername" /></td>
				<td><s:if test="state==1">
						<span style="color: green; font-weight: bold;"><s:property
								value="score" /></span>
					</s:if> <s:elseif test="state==2">
						<span style="color: red; font-weight: bold;"><s:property
								value="score" /></span>
					</s:elseif></td>
				<td><s:if test="state==0">未听课</s:if> <s:elseif test="state==1">
						<span style="color: green; font-weight: bold;">合格</span>
					</s:elseif> <s:elseif test="state==2">
						<span style="color: red; font-weight: bold;">不合格</span>
					</s:elseif></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<div class="dataTables_info" id="dyntable2_info"
	style="margin-left: 20px; margin-right: 20px;">
	<span class="txt-smaller txt-light"> 当前共有<span class="number"
		style="color: red;"><s:property value="resultCount" /> </span>条评分记录
	</span>
</div>
<div class="dataTables_paginate paging_full_numbers"
	style="margin-left: 20px; margin-right: 20px;">
	<jsp:include page="/template/page.jsp"></jsp:include>
</div>