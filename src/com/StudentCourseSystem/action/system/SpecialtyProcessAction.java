package com.StudentCourseSystem.action.system;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.StudentCourseSystem.Service.ISpecialtyService;
import com.StudentCourseSystem.bean.TSpecialty;
import com.StudentCourseSystem.tool.BaseProcessAction;
import com.StudentCourseSystem.tool.SystemUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/specialty")
public class SpecialtyProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = 6352988498791890510L;
	private TSpecialty specialty = new TSpecialty();
	private ISpecialtyService specialtyService;
	private String allIds = null;
	private String message = "0";

	@Action(value = "addProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String add() {
		Long maxid = specialtyService.getMaxId();
		if (maxid != null) {
			specialty.setId(maxid + 1);
		} else {
			specialty.setId(1);
		}
		specialty.setCreateDate(SystemUtil
				.getSystemDateTime("yyyy-MM-dd HH:mm"));
		specialtyService.addSpecialty(specialty);
		return SUCCESS;
	}

	@Action(value = "modifyProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String modify() {
		specialtyService.modifySpecialty(specialty);
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
	public TSpecialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(TSpecialty specialty) {
		this.specialty = specialty;
	}

	@JSON(serialize = false)
	public ISpecialtyService getSpecialtyService() {
		return specialtyService;
	}

	@Resource
	public void setSpecialtyService(ISpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

}
