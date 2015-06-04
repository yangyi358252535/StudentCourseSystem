<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="vernav2 iconmenu" id="leftmenu">
	<ul>
		<s:iterator value="#session.BO_MenuAndAuthInfoList" var="menuList"
			status="var">
			<s:if test="#var.count==1"> 
				<li><a href="#" class="widgets"><s:property
						value="#menuList.keySet()" /></a> <span class="arrow"></span>
				<ul style="display: block;">
					<s:iterator value="#menuList.keySet()" id="menusValueIndex">
						<s:iterator value="#menuList.get(#menusValueIndex)"
							id="menuValueIndex">
							<li><a href="#" 
							name='<s:property value="#menuValueIndex.source_Url"/>'
							title='<s:property value="#menuValueIndex.authName_Chinese"/>'><s:property
											value="#menuValueIndex.authName_Chinese" /></a></li>
						</s:iterator>
					</s:iterator>
				</ul></li>
			</s:if>
			<s:else>
			<li><a href="#" class="widgets"><s:property
						value="#menuList.keySet()" /></a> <span class="arrow"></span>
				<ul style="display: none;">
					<s:iterator value="#menuList.keySet()" id="menusValueIndex">
						<s:iterator value="#menuList.get(#menusValueIndex)"
							id="menuValueIndex">
							<li><a href="#" 
							name='<s:property value="#menuValueIndex.source_Url"/>'
							title='<s:property value="#menuValueIndex.authName_Chinese"/>'><s:property
											value="#menuValueIndex.authName_Chinese" /></a></li>
						</s:iterator>
					</s:iterator>
				</ul></li>
			</s:else>
		</s:iterator>
	</ul>
	<a class="togglemenu"></a> <br /> <br />
</div>
