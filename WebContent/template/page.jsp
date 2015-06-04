<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="isFirstAvailiable()==false">
	<span class="first paginate_button paginate_button_disabled">首页</span>
</s:if>
<s:elseif test="isFirstAvailiable()==true">
	<span class="first paginate_button" id="firstPage">首页</span>
</s:elseif>
<s:if test="isPreviousAvailiable()==false">
	<span class="previous paginate_button paginate_button_disabled">上一页</span>
</s:if>
<s:elseif test="isPreviousAvailiable()==true">
	<span class="previous paginate_button" id="prePage">上一页</span>
</s:elseif>
<span> 第<span class="paginate_active" id="thisPage"> <s:property
			value="currentPage" /></span>页&nbsp;&nbsp; 共 <span class="allpage"
	id="pageAll"><s:property value="totalPageAmount" /> </span> 页
</span>
<s:if test="isNextAvailiable()==false">
	<span class="next paginate_button paginate_button_disabled">下一页</span>
</s:if>
<s:elseif test="isNextAvailiable()==true">
	<span class="next paginate_button" id="nextPage">下一页</span>
</s:elseif>
<s:if test="isLastAvailiable()==false">
	<span class="last paginate_button paginate_button_disabled">尾页</span>
</s:if>
<s:elseif test="isLastAvailiable()==true">
	<span class="last paginate_button" id="lastPage">尾页</span>
</s:elseif>
<span><s:textfield
			cssStyle="text-align: center;width:30px;height:13px;" id="jumpPage" />&nbsp;&nbsp;<a
		href="#" class="btn btn-warning btn-mini"
		style="position: relative; top: -3px;" id="jump">跳转</a> </span>
