package com.StudentCourseSystem.action.system;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.StudentCourseSystem.Service.ICourseService;
import com.StudentCourseSystem.bean.TCourse;
import com.StudentCourseSystem.tool.BaseProcessAction;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/course")
public class CourseProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = 4905860383844315738L;
	private TCourse course = new TCourse();
	private ICourseService courseService;
	private String allIds = null;
	private String message = "0";

	@Action(value = "addProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "",
			"includeProperties", "message" }) })
	public String add() {
		Long maxid = courseService.getMaxId();
		if (maxid != null) {
			course.setId(maxid + 1);
		} else {
			course.setId(1);
		}
		courseService.addCourse(course);
		return SUCCESS;
	}

	@Action(value = "modifyProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String modify() {
		courseService.modifyCourse(course);
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
}
