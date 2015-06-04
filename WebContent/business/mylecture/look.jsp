<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form style="margin-left: 20px; margin-right: 20px;"
	class="stdform stdform2" id="editForm">
	<p>
		<label>课程名称</label> <span class="field"> <s:property
				value="lecture.course.name" />
		</span>
	</p>
	<p>
		<label>记录创建时间</label> <span class="field"> <s:property
				value="lecture.createDate" />
		</span>
	</p>
	<p>
		<label>上课周数</label> <span class="field"> <s:property
				value="lecture.course.zhouci.mastername" />
		</span>
	</p>
	<p>
		<label>上课节数</label> <span class="field"> <s:property
				value="lecture.course.jieshu.mastername" />
		</span>
	</p>
	<p>
		<label>上课位置</label> <span class="field"> <s:property
				value="lecture.course.weizhi.mastername" />
		</span>
	</p>
	<p>
		<label>听课分数</label> <span class="field"> <s:property
				value="lecture.score" />
		</span>
	</p>
	<p>
		<label>听课记录评分状态</label> <span class="field"> <s:if
				test="lecture.state==0">未听课</s:if> <s:elseif test="lecture.state==1">合格</s:elseif>
			<s:elseif test="lecture.state==2">不合格</s:elseif>
		</span>
	</p>
	<p class="stdformbutton">
		<button class="stdbtn btn_black" type="button" id="cancel_b">返回</button>
	</p>
</form>
