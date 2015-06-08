package com.StudentCourseSystem.action.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.StudentCourseSystem.Service.ICourseService;
import com.StudentCourseSystem.Service.IScoreService;
import com.StudentCourseSystem.Service.IStudentService;
import com.StudentCourseSystem.bean.TClass;
import com.StudentCourseSystem.bean.TCourse;
import com.StudentCourseSystem.bean.TScore;
import com.StudentCourseSystem.bean.TStudent;
import com.StudentCourseSystem.bean.TTeacher;
import com.StudentCourseSystem.tool.PagingUtil;
import com.StudentCourseSystem.tool.ScoreComparable;
import com.StudentCourseSystem.tool.SystemConstant;


@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/business/score")
@Results({
		@Result(name = "toList", location = "/business/score/list.jsp"),
		@Result(name = "toMain", location = "/business/score/main.jsp"),
		@Result(name = "toModify", location = "/business/score/modify.jsp"),
		@Result(name = "toAdd", location = "/business/score/add.jsp"),
		@Result(name = "toInput", location = "/business/score/input.jsp"),
		@Result(name = "toAward", location = "/business/score/addAward.jsp"),
		@Result(name = "LoadId", type = "json", params = { "includeProperties",
				"currentPageIds" }),
		@Result(name = "LoadAllId", type = "json", params = {
				"includeProperties", "currentAllIds" }) })
public class StudentScoreMainAction extends PagingUtil<TStudent> {
	private static final long serialVersionUID = -5943595137712975879L;
	private IStudentService studentService;
	private IClaszService claszService;
	private IScoreService scoreService;
	private ICourseService courseService;
	private TStudent student;
	private List<TScore> scoreList;
	private List<TClass> claszList;
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	private String condition_string;
	{
		setClass(TStudent.class, "student");
		// 每页显示十条数据
		setPageableAmount(10);
		setOrderSql(" order by student.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchStudent();
		return "toList";
	}

	@Action(value = "toAdd")
	public String toAdd() {
		claszList = claszService.getAllTheClazz();
		return "toAdd";
	}

	@Action(value = "toModify")
	public String toModify() {
		claszList = claszService.getAllTheClazz();
		student = studentService.getStudent(student.getId());
		return "toModify";
	}

	@Action(value = "toInput")
	public String toInput() {
		Set<TScore> scores = new HashSet<TScore>();
		student = studentService.getStudent(student.getId());
		scoreList = new ArrayList<TScore>();
		scoreList.addAll(student.getScores());
		if (student.getScores()==null||scoreList.size() == 0) {
			List<TCourse> masters = courseService.getCourseBySpecialty(student.getClasz().getSpecialty().getId());
			TScore score = null;
			Long maxidx = null;
			for (TCourse master : masters) {
				score = new TScore();
				maxidx = scoreService.getMaxId();
				if (maxidx != null) {
					score.setId(maxidx + 1);
				} else {
					score.setId(1);
				}
				score.setCreateDate("");
				score.setDeleteflag(0);
				score.setSudentName(student.getName());
				score.setScore(0);
				score.setType(master);
				scoreService.addScore(score);
				scoreService.updateStudentIdForScore(student.getId(),
						score.getId());
				scores.add(score);
			}
			scoreList.addAll(scores);
		}
		ScoreComparable comparable = new ScoreComparable();
		comparable.sortASC = true;
		Collections.sort(scoreList, comparable);
		return "toInput";
	}

	private void searchStudent() {
		TTeacher teacher = (TTeacher) getSession().get(
				SystemConstant.CURRENTUSER);
		if ("2".equals(flagString)) {
			setSQL(" and student.name like ? ");
			queryParameters.add("%" + information + "%");
		} else if ("1".equals(flagString)) {
			setSQL(" and student.num like ? ");
			queryParameters.add("%" + information + "%");
		}
		setSQL(" and student.clasz.specialty.id =" + teacher.getSpecialty().getId());
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
		searchStudent();
		return "toList";
	}

	@Action(value = "toPrevious")
	public String toPrevious() {
		previous();
		searchStudent();
		return "toList";
	}

	@Action(value = "toLast")
	public String toLast() {
		last();
		searchStudent();
		return "toList";
	}

	@Action(value = "toFirst")
	public String toFirst() {
		first();
		searchStudent();
		return "toList";
	}

	@Action(value = "toPage")
	public String toPage() {
		page();
		searchStudent();
		return "toList";
	}

	@Action(value = "toReload")
	public String toReload() {
		searchStudent();
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
	public IStudentService getStudentService() {
		return studentService;
	}

	@Resource
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	@JSON(serialize = false)
	public TStudent getStudent() {
		return student;
	}

	public void setStudent(TStudent student) {
		this.student = student;
	}

	@JSON(serialize = false)
	public List<TClass> getClaszList() {
		return claszList;
	}

	public void setClaszList(List<TClass> claszList) {
		this.claszList = claszList;
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
	public List<TScore> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<TScore> scoreList) {
		this.scoreList = scoreList;
	}

	@JSON(serialize = false)
	public IScoreService getScoreService() {
		return scoreService;
	}

	@Resource
	public void setScoreService(IScoreService scoreService) {
		this.scoreService = scoreService;
	}

	@JSON(serialize = false)
	public String getCondition_string() {
		return condition_string;
	}

	public void setCondition_string(String condition_string) {
		this.condition_string = condition_string;
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
