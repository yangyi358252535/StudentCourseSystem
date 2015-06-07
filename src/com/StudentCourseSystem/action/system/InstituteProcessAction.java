package com.StudentCourseSystem.action.system;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.StudentCourseSystem.Service.IInstituteService;
import com.StudentCourseSystem.bean.TInstitute;
import com.StudentCourseSystem.tool.BaseProcessAction;
import com.StudentCourseSystem.tool.SystemUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/institute")
public class InstituteProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = 1125341968002487000L;
	private String allIds = null;
	private String message = "0";
	private TInstitute institute=new TInstitute();
	private IInstituteService instituteService;
	@Action(value = "addProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String add() {
		Long maxid = instituteService.getMaxId();
		if (maxid != null) {
			institute.setId(maxid + 1);
		} else {
			institute.setId(1);
		}
		institute.setCreateDate(SystemUtil.getSystemDateTime("yyyy-MM-dd HH:mm"));
		institute.setDeleteflag(0);
		instituteService.addInstitute(institute);
		return SUCCESS;
	}

	@Action(value = "modifyProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String modify() {
		instituteService.modifyInstitute(institute);
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
	public TInstitute getInstitute() {
		return institute;
	}

	public void setInstitute(TInstitute institute) {
		this.institute = institute;
	}
	@JSON(serialize = false)
	public IInstituteService getInstituteService() {
		return instituteService;
	}
	@Resource
	public void setInstituteService(IInstituteService instituteService) {
		this.instituteService = instituteService;
	}
}
