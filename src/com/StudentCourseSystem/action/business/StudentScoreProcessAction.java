package com.StudentCourseSystem.action.business;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.StudentCourseSystem.Service.IScoreService;
import com.StudentCourseSystem.bean.TScore;
import com.StudentCourseSystem.bean.TStudent;
import com.StudentCourseSystem.tool.BaseProcessAction;
import com.StudentCourseSystem.tool.SystemUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/business/score")
public class StudentScoreProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = -7518344973574116467L;
	private IScoreService scoreService;
	private TStudent student = new TStudent();
	private String allIds = null;
	private long[] id;
	private float[] score;

	@Action(value = "scoreProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String score() {
		TScore scoreT = null;
		for (int i = 0; i < id.length; i++) {
			scoreT = scoreService.getScore(id[i]);
			scoreT.setScore(score[i]);
			scoreT.setCreateDate(SystemUtil
					.getSystemDateTime(" yyyy-MM-dd HH:mm"));
			scoreService.modifyScore(scoreT);
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


	@JSON(serialize = false)
	public TStudent getStudent() {
		return student;
	}

	public void setStudent(TStudent student) {
		this.student = student;
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
	public long[] getId() {
		return id;
	}

	public void setId(long[] id) {
		this.id = id;
	}

	@JSON(serialize = false)
	public float[] getScore() {
		return score;
	}

	public void setScore(float[] score) {
		this.score = score;
	}

}
