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

import com.StudentCourseSystem.Service.IInstituteService;
import com.StudentCourseSystem.Service.ISpecialtyService;
import com.StudentCourseSystem.bean.TInstitute;
import com.StudentCourseSystem.bean.TSpecialty;
import com.StudentCourseSystem.tool.PagingUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/specialty")
@Results({
		@Result(name = "toList", location = "/system/specialty/list.jsp"),
		@Result(name = "toMain", location = "/system/specialty/main.jsp"),
		@Result(name = "toModify", location = "/system/specialty/modify.jsp"),
		@Result(name = "toAdd", location = "/system/specialty/add.jsp"),
		@Result(name = "LoadId", type = "json", params = { "includeProperties",
				"currentPageIds" }),
		@Result(name = "LoadAllId", type = "json", params = {
				"includeProperties", "currentAllIds" }) })
public class SpecialtyMainAction extends PagingUtil<TSpecialty> {
	private static final long serialVersionUID = 533978026665638195L;
	private TSpecialty specialty;
	private ISpecialtyService specialtyService;
	private IInstituteService instituteService;
	private List<TInstitute> instituteList;
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	{
		setClass(TSpecialty.class, "specialty");
		setPageableAmount(10);
		setOrderSql(" order by specialty.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchSpecialty();
		return "toList";
	}

	@Action(value = "toAdd")
	public String toAdd() {
		instituteList=instituteService.getAllTheInstitute();
		return "toAdd";
	}

	@Action(value = "toModify")
	public String toModify() {
		specialty=specialtyService.getSpecialty(specialty.getId());
		instituteList=instituteService.getAllTheInstitute();
		return "toModify";
	}

	private void searchSpecialty() {
		if ("1".equals(flagString)) {
			setSQL(" and specialty.name like ? ");
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
		searchSpecialty();
		return "toList";
	}

	@Action(value = "toPrevious")
	public String toPrevious() {
		previous();
		searchSpecialty();
		return "toList";
	}

	@Action(value = "toLast")
	public String toLast() {
		last();
		searchSpecialty();
		return "toList";
	}

	@Action(value = "toFirst")
	public String toFirst() {
		first();
		searchSpecialty();
		return "toList";
	}

	@Action(value = "toPage")
	public String toPage() {
		page();
		searchSpecialty();
		return "toList";
	}

	@Action(value = "toReload")
	public String toReload() {
		searchSpecialty();
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
	@JSON(serialize = false)
	public List<TInstitute> getInstituteList() {
		return instituteList;
	}

	public void setInstituteList(List<TInstitute> instituteList) {
		this.instituteList = instituteList;
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
