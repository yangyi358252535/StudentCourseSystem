package com.StudentCourseSystem.action.business;

import java.util.List;

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
@Namespace("/business/course")
public class StudentScoreProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = 4905860383844315738L;
	private TCourse course = new TCourse();
	private ICourseService courseService;
	private String allIds = null;
	private String message = "0";

	@Action(value = "addProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "","includeProperties", "message" }) })
	public String add() {
		List<TCourse> tmp=courseService.getCurrentCourse(course.getSpecialty().getId()
				, course.getJieshu().getMasterid(), course.getWeizhi().getMasterid());
		if(tmp!=null&&tmp.size()>0){
			message="1";
			return SUCCESS;
		}
		Long maxid = courseService.getMaxId();
		if (maxid != null) {
			course.setId(maxid + 1);
		} else {
			course.setId(1);
		}
		course.setCurrentStuedentCount(0);
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

	@Action(value = "checkExsitProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "",
			"includeProperties", "message" }) })
	public String checkExsit() {
		List<TCourse> courseList = courseService.getCurrentCourse(course
				.getZhouci().getMasterid(), course.getJieshu().getMasterid(),
				course.getWeizhi().getMasterid());
		if (courseList != null && courseList.size() > 0) {
			message = "1";
		}
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
