package com.StudentCourseSystem.action.system;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.StudentCourseSystem.Service.IInstituteService;
import com.StudentCourseSystem.bean.TInstitute;
import com.StudentCourseSystem.tool.PagingUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/institute")
@Results({
		@Result(name = "toList", location = "/system/institute/list.jsp"),
		@Result(name = "toMain", location = "/system/institute/main.jsp"),
		@Result(name = "toModify", location = "/system/institute/modify.jsp"),
		@Result(name = "toAdd", location = "/system/institute/add.jsp"),
		@Result(name = "LoadId", type = "json", params = { "includeProperties",
				"currentPageIds" }),
		@Result(name = "LoadAllId", type = "json", params = {
				"includeProperties", "currentAllIds" }) })
public class InstituteMainAction extends PagingUtil<TInstitute> {
	private static final long serialVersionUID = 8741448318755394142L;
	private TInstitute institute;
	private IInstituteService instituteService;
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	{
		setClass(TInstitute.class, "institute");
		setPageableAmount(10);
		setOrderSql(" order by institute.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchInstitute();
		return "toList";
	}

	@Action(value = "toAdd")
	public String toAdd() {
		return "toAdd";
	}

	@Action(value = "toModify")
	public String toModify() {
		return "toModify";
	}

	private void searchInstitute() {
		if ("1".equals(flagString)) {
			setSQL(" and institute.name like ? ");
			queryParameters.add("%" + information + "%");
		}
		search();
	}

	@Action(value = "uploadIdCollection", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json") })
	public String uploadIdCollection() {
		updateIdCollectionInSession();
		return SUCCESS;
	}

	@Action(value = "loadIdCollection", params = { "contentType",
			"text/html;charset=UTF-8" })
	public String loadIdCollection() {
		this.currentPageIds = getIdCollectionFromSession();
		return "LoadId";
	}

	@Action(value = "loadAllIdCollection", params = { "contentType",
			"text/html;charset=UTF-8" })
	public String loadAllIdCollection() {
		this.currentAllIds = getAllIdsFromSession();
		return "LoadAllId";
	}

	@Override
	public String getCurrentPageIds() {
		return currentPageIds;
	}

	@Override
	public void setCurrentPageIds(String currentPageIds) {
		this.currentPageIds = currentPageIds;
	}

	@Override
	public String getCurrentAllIds() {
		return this.currentAllIds;
	}

	@Override
	public void setCurrentAllIds(String currentAllIds) {
		this.currentAllIds = currentAllIds;
	}

	@Action(value = "toNext")
	public String toNext() {
		next();
		searchInstitute();
		return "toList";
	}

	@Action(value = "toPrevious")
	public String toPrevious() {
		previous();
		searchInstitute();
		return "toList";
	}

	@Action(value = "toLast")
	public String toLast() {
		last();
		searchInstitute();
		return "toList";
	}

	@Action(value = "toFirst")
	public String toFirst() {
		first();
		searchInstitute();
		return "toList";
	}

	@Action(value = "toPage")
	public String toPage() {
		page();
		searchInstitute();
		return "toList";
	}

	@Action(value = "toReload")
	public String toReload() {
		searchInstitute();
		return "toList";
	}

	public String getFlagString() {
		return flagString;
	}

	public void setFlagString(String flagString) {
		this.flagString = flagString;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
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
