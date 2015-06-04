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
import com.StudentCourseSystem.Service.ITeacherService;
import com.StudentCourseSystem.bean.TClass;
import com.StudentCourseSystem.bean.TSpecialty;
import com.StudentCourseSystem.bean.TTeacher;
import com.StudentCourseSystem.tool.PagingUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/teacher")
@Results({
		@Result(name = "toList", location = "/system/teacher/list.jsp"),
		@Result(name = "toMain", location = "/system/teacher/main.jsp"),
		@Result(name = "toModify", location = "/system/teacher/modify.jsp"),
		@Result(name = "toAdd", location = "/system/teacher/add.jsp"),
		@Result(name = "LoadId", type = "json", params = { "includeProperties",
				"currentPageIds" }),
		@Result(name = "LoadAllId", type = "json", params = {
				"includeProperties", "currentAllIds" }) })
public class TeacherMainAction extends PagingUtil<TTeacher> {
	private static final long serialVersionUID = -3556370317329222933L;
	private TTeacher teacher=new TTeacher();
	private ITeacherService teacherService;
	private IClaszService claszService;
	private List<TSpecialty> specialtyList;
	private ISpecialtyService specialtyService;
	private List<TClass> claszList;
	private long oldClassId;
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	{
		setClass(TTeacher.class, "teacher");
		setPageableAmount(10);
		setOrderSql(" order by teacher.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchTeacher();
		return "toList";
	}

	@Action(value = "toAdd")
	public String toAdd() {
		specialtyList=specialtyService.getAllSpecialty();
		return "toAdd";
	}

	@Action(value = "toModify")
	public String toModify() {
		specialtyList=specialtyService.getAllSpecialty();
		teacher=teacherService.getTeacher(teacher.getId());
		return "toModify";
	}

	private void searchTeacher() {
		if ("2".equals(flagString)) {
			setSQL(" and teacher.name like ? ");
			queryParameters.add("%" + information + "%");
		} else if ("1".equals(flagString)) {
			setSQL(" and teacher.num like ? ");
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
		searchTeacher();
		return "toList";
	}

	@Action(value = "toPrevious")
	public String toPrevious() {
		previous();
		searchTeacher();
		return "toList";
	}

	@Action(value = "toLast")
	public String toLast() {
		last();
		searchTeacher();
		return "toList";
	}

	@Action(value = "toFirst")
	public String toFirst() {
		first();
		searchTeacher();
		return "toList";
	}

	@Action(value = "toPage")
	public String toPage() {
		page();
		searchTeacher();
		return "toList";
	}

	@Action(value = "toReload")
	public String toReload() {
		searchTeacher();
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
	public TTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(TTeacher teacher) {
		this.teacher = teacher;
	}

	@JSON(serialize = false)
	public ITeacherService getTeacherService() {
		return teacherService;
	}

	@Resource
	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@JSON(serialize = false)
	public IClaszService getClaszService() {
		return claszService;
	}

	public void setClaszService(IClaszService claszService) {
		this.claszService = claszService;
	}

	@JSON(serialize = false)
	public long getOldClassId() {
		return oldClassId;
	}

	public void setOldClassId(long oldClassId) {
		this.oldClassId = oldClassId;
	}

	@JSON(serialize = false)
	public List<TClass> getClaszList() {
		return claszList;
	}

	public void setClaszList(List<TClass> claszList) {
		this.claszList = claszList;
	}

	@JSON(serialize = false)
	public List<TSpecialty> getSpecialtyList() {
		return specialtyList;
	}

	public void setSpecialtyList(List<TSpecialty> specialtyList) {
		this.specialtyList = specialtyList;
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
