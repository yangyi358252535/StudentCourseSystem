package com.StudentCourseSystem.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.StudentCourseSystem.Service.IClaszService;
import com.StudentCourseSystem.Service.ISpecialtyService;
import com.StudentCourseSystem.bean.TClass;
import com.StudentCourseSystem.bean.TSpecialty;
import com.StudentCourseSystem.tool.PagingUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/clazz")
@Results({
		@Result(name = "toList", location = "/system/clazz/list.jsp"),
		@Result(name = "toMain", location = "/system/clazz/main.jsp"),
		@Result(name = "toModify", location = "/system/clazz/modify.jsp"),
		@Result(name = "toAdd", location = "/system/clazz/add.jsp"),
		@Result(name = "LoadId", type = "json", params = { "includeProperties",
				"currentPageIds" }),
		@Result(name = "LoadAllId", type = "json", params = {
				"includeProperties", "currentAllIds" }) })
public class ClazzMainAction extends PagingUtil<TClass> {
	private static final long serialVersionUID = 4952957180855829594L;
	private TClass clasz;
	private IClaszService claszService;
	private ISpecialtyService specialtyService;
	private List<TSpecialty> specialtyList;
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	{
		setClass(TClass.class, "clasz");
		setPageableAmount(10);
		setOrderSql(" order by clasz.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchClasz();
		return "toList";
	}

	@Action(value = "toAdd")
	public String toAdd() {
		specialtyList=specialtyService.getAllSpecialty();
		return "toAdd";
	}

	@Action(value = "toModify")
	public String toModify() {
		clasz = claszService.getClasz(clasz.getId());
		specialtyList=specialtyService.getAllSpecialty();
		return "toModify";
	}

	private void searchClasz() {
		if ("1".equals(flagString)) {
			setSQL(" and clasz.name like ? ");
			queryParameters.add("%" + information + "%");
		}else if("2".equals(flagString)){
			setSQL(" and clasz.specialty.name like ? ");
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
		searchClasz();
		return "toList";
	}

	@Action(value = "toPrevious")
	public String toPrevious() {
		previous();
		searchClasz();
		return "toList";
	}

	@Action(value = "toLast")
	public String toLast() {
		last();
		searchClasz();
		return "toList";
	}

	@Action(value = "toFirst")
	public String toFirst() {
		first();
		searchClasz();
		return "toList";
	}

	@Action(value = "toPage")
	public String toPage() {
		page();
		searchClasz();
		return "toList";
	}

	@Action(value = "toReload")
	public String toReload() {
		searchClasz();
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

	@JSON(serialize = false)
	public ISpecialtyService getSpecialtyService() {
		return specialtyService;
	}

	@Resource
	public void setSpecialtyService(ISpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@JSON(serialize = false)
	public List<TSpecialty> getSpecialtyList() {
		return specialtyList;
	}

	public void setSpecialtyList(List<TSpecialty> specialtyList) {
		this.specialtyList = specialtyList;
	}

}
