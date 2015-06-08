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

import com.StudentCourseSystem.Service.ICourseService;
import com.StudentCourseSystem.Service.ISpecialtyService;
import com.StudentCourseSystem.bean.TCourse;
import com.StudentCourseSystem.bean.TSpecialty;
import com.StudentCourseSystem.bean.TTeacher;
import com.StudentCourseSystem.tool.PagingUtil;
import com.StudentCourseSystem.tool.SystemConstant;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/course")
@Results({
		@Result(name = "toList", location = "/system/course/list.jsp"),
		@Result(name = "toMain", location = "/system/course/main.jsp"),
		@Result(name = "toModify", location = "/system/course/modify.jsp"),
		@Result(name = "toAdd", location = "/system/course/add.jsp"),
		@Result(name = "LoadId", type = "json", params = { "includeProperties",
				"currentPageIds" }),
		@Result(name = "LoadAllId", type = "json", params = {
				"includeProperties", "currentAllIds" }) })
public class CourseMainAction extends PagingUtil<TCourse> {
	private static final long serialVersionUID = -5713017370765183339L;
	private TCourse course;
	private ICourseService courseService;
	private ISpecialtyService specialtyService;
	private List<TSpecialty> specialtyList;
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	{
		setClass(TCourse.class, "course");
		setPageableAmount(10);
		setOrderSql(" order by course.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchCourse();
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
		course = courseService.getCourse(course.getId());
		return "toModify";
	}

	private void searchCourse() {
		Object teacher = getSession().get(SystemConstant.CURRENTUSER);
		if ("2".equals(flagString)) {
			setSQL(" and course.teacher.name like ? ");
			queryParameters.add("%" + information + "%");
		} else if ("1".equals(flagString)) {
			setSQL(" and course.name like ? ");
			queryParameters.add("%" + information + "%");
		}
		if (teacher instanceof TTeacher) {
			setSQL(" and course.specialty.id ="
					+ ((TTeacher) teacher).getSpecialty().getId());
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
		searchCourse();
		return "toList";
	}

	@Action(value = "toPrevious")
	public String toPrevious() {
		previous();
		searchCourse();
		return "toList";
	}

	@Action(value = "toLast")
	public String toLast() {
		last();
		searchCourse();
		return "toList";
	}

	@Action(value = "toFirst")
	public String toFirst() {
		first();
		searchCourse();
		return "toList";
	}

	@Action(value = "toPage")
	public String toPage() {
		page();
		searchCourse();
		return "toList";
	}

	@Action(value = "toReload")
	public String toReload() {
		searchCourse();
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
	public TCourse getCourse() {
		return course;
	}

	public void setCourse(TCourse course) {
		this.course = course;
	}

	@JSON(serialize = false)
	public ICourseService getCourseService() {
		return courseService;
	}

	@Resource
	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
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