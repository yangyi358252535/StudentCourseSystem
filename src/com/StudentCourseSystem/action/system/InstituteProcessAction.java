package com.StudentCourseSystem.action.system;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.StudentCourseSystem.Service.IClaszService;
import com.StudentCourseSystem.bean.TClass;
import com.StudentCourseSystem.tool.BaseProcessAction;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/clazz")
public class InstituteProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = -2150253813306906815L;
	private TClass clasz;
	private IClaszService claszService;
	private String allIds = null;
	private String message = "0";

	@Action(value = "addProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String add() {
		Long maxid = claszService.getMaxId();
		if (maxid != null) {
			clasz.setId(maxid + 1);
		} else {
			clasz.setId(1);
		}
		claszService.addClasz(clasz);
		return SUCCESS;
	}

	@Action(value = "modifyProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String modify() {
		claszService.modifyClasz(clasz);
		return SUCCESS;
	}

	@Action(value = "clearSessionForSearch", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "clearSesssion", type = "json") })
	public String clearSessionForSearch() {
		clearSession();
		return "clearSesssion";
	}

	@Override
	public String getAllIds() {
		return allIds;
	}

	@Override
	public void setAllIds(String allIds) {
		this.allIds = allIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public TClass getClasz() {
		return clasz;
	}

	public void setClasz(TClass clasz) {
		this.clasz = clasz;
	}

	@JSON(serialize = false)
	public IClaszService getClaszService() {
		return claszService;
	}

	@Resource
	public void setClaszService(IClaszService claszService) {
		this.claszService = claszService;
	}
}
