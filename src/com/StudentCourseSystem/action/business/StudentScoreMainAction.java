package com.StudentCourseSystem.action.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
		@Result(name = "toLook", location = "/business/score/look.jsp"),
		@Result(name = "toInput", location = "/business/score/input.jsp"),
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
	private List<TCourse> courseList;
	private List<TScore> scoreList;
	private List<TClass> claszList;
	private String year_str="2015";
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	private String condition_string;
	private int showflag=0;
	private String term_str="1";
	{
		setClass(TStudent.class, "student");
		// 每页显示十条数据
		setPageableAmount(10);
		setOrderSql(" order by student.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		Object user = getSession().get(SystemConstant.CURRENTUSER);
		if(user instanceof TTeacher){
			showflag=1;
		}else{
			showflag=0;
		}
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchStudent();
		return "toList";
	}
	@Action(value = "toLook")
	public String toLook() {
		student = studentService.getStudent(student.getId());
		scoreList = new ArrayList<TScore>();
		for (TScore score1 : student.getScores()) {
			if (score1.getYear_str().equals(year_str)&&score1.getType().getTerm().getMasterid()==14&&term_str.equals("1")) {
				scoreList.add(score1);
			}else if(score1.getYear_str().equals(year_str)&&score1.getType().getTerm().getMasterid()==15&&term_str.equals("2")){
				scoreList.add(score1);
			}
		}
		ScoreComparable comparable = new ScoreComparable();
		comparable.sortASC = true;
		Collections.sort(scoreList, comparable);
		return "toLook";
	}

	@Action(value = "toInput")
	public String toInput() {
		Set<TScore> scores = new HashSet<TScore>();
		student = studentService.getStudent(student.getId());
		scoreList = new ArrayList<TScore>();
		for (TScore score1 : student.getScores()) {
			if (score1.getType().getYear_str().equals(year_str)&&score1.getType().getTerm().getMasterid()==14&&term_str.equals("1")) {
				scoreList.add(score1);
			}else if(score1.getType().getYear_str().equals(year_str)&&score1.getType().getTerm().getMasterid()==15&&term_str.equals("2")){
				scoreList.add(score1);
			}
		}
		if (scoreList.size() == 0) {
			List<TCourse> masters =null;
			if(term_str.equals("1")){
				masters=courseService.getCourseBySpecialty(student.getClasz().getSpecialty().getId(),year_str,14);
			}else if(term_str.equals("2")){
				masters=courseService.getCourseBySpecialty(student.getClasz().getSpecialty().getId(),year_str,15);
			}
			
			TScore score = null;
			Long maxidx = null;
			if(term_str.equals("1")){
				for (TCourse master : masters) {
					if(master.getTerm().getMasterid()==14){
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
						score.setSudentId(student.getId());
						score.setScore(0);
						score.setYear_str(year_str);
						score.setType(master);
						scoreService.addScore(score);
						scoreService.updateStudentIdForScore(student.getId(),
								score.getId());
						scores.add(score);
					}
				}
			}else if(term_str.equals("2")){
				for (TCourse master : masters) {
					if(master.getTerm().getMasterid()==15){
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
						score.setSudentId(student.getId());
						score.setScore(0);
		//					score.setTerm(scoreService.getTheMasterById(15));
						score.setYear_str(year_str);
						score.setType(master);
						scoreService.addScore(score);
						scoreService.updateStudentIdForScore(student.getId(),
								score.getId());
						scores.add(score);
					}
				}
			}
			scoreList.addAll(scores);
		}else{
			int scoresize=scoreList.size();
			if(term_str.equals("1")){
				courseList=courseService.getCourseBySpecialty(student.getClasz().getSpecialty().getId(),year_str,14);
			}else if(term_str.equals("2")){
				courseList=courseService.getCourseBySpecialty(student.getClasz().getSpecialty().getId(),year_str,15);
			}
			Map<Long,Integer> dataMap=new HashMap<Long,Integer>();
			if(courseList.size()>scoresize){
				for (TCourse c : courseList) {
					dataMap.put(c.getId(), 0);
					for (TScore s : scoreList) {
						if ((s.getType().getName()).equals(c.getName())) {
							dataMap.put(c.getId(), 1);
							break;
						}
					}
				}
			}
			TScore score = null;
			Long maxidx = null;
			for (Long key : dataMap.keySet()) {
				if(dataMap.get(key)==0){
					if(term_str.equals("1")){
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
						score.setSudentId(student.getId());
						score.setScore(0);
//						score.setTerm(scoreService.getTheMasterById(14));
						score.setYear_str(year_str);
						score.setType(courseService.getCourse(key));
						scoreService.addScore(score);
						scoreService.updateStudentIdForScore(student.getId(),
								score.getId());
						scoreList.add(score);
					}else if(term_str.equals("2")){
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
						score.setSudentId(student.getId());
						score.setScore(0);
//						score.setTerm(scoreService.getTheMasterById(15));
						score.setYear_str(year_str);
						score.setType(courseService.getCourse(key));
						scoreService.addScore(score);
						scoreService.updateStudentIdForScore(student.getId(),
								score.getId());
						scoreList.add(score);
					}
				}
			}
		}
		ScoreComparable comparable = new ScoreComparable();
		comparable.sortASC = true;
		Collections.sort(scoreList, comparable);
		return "toInput";
	}

	private void searchStudent() {
		if ("2".equals(flagString)) {
			setSQL(" and student.name like ? ");
			queryParameters.add("%" + information + "%");
		} else if ("1".equals(flagString)) {
			setSQL(" and student.num like ? ");
			queryParameters.add("%" + information + "%");
		}
		Object user = getSession().get(SystemConstant.CURRENTUSER);
		if(user instanceof TTeacher){
			TTeacher t=(TTeacher)user;
			setSQL(" and student.clasz.specialty.id="+t.getSpecialty().getId());
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
	@JSON(serialize = false)
	public String getYear_str() {
		return year_str;
	}
	public void setYear_str(String year_str) {
		this.year_str = year_str;
	}
	@JSON(serialize = false)
	public int getShowflag() {
		return showflag;
	}

	public void setShowflag(int showflag) {
		this.showflag = showflag;
	}
	@JSON(serialize = false)
	public List<TCourse> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<TCourse> courseList) {
		this.courseList = courseList;
	}
	@JSON(serialize = false)
	public String getTerm_str() {
		return term_str;
	}
	public void setTerm_str(String term_str) {
		this.term_str = term_str;
	}

}
