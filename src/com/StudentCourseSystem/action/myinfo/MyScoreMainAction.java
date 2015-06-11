package com.StudentCourseSystem.action.myinfo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.StudentCourseSystem.bean.TScore;
import com.StudentCourseSystem.bean.TStudent;
import com.StudentCourseSystem.tool.PagingUtil;
import com.StudentCourseSystem.tool.SystemConstant;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/myinfo/score")
@Results({
		@Result(name = "toList", location = "/myinfo/score/list.jsp"),
		@Result(name = "toMain", location = "/myinfo/score/main.jsp"),
		@Result(name = "LoadId", type = "json", params = { "includeProperties",
				"currentPageIds" }),
		@Result(name = "LoadAllId", type = "json", params = {
				"includeProperties", "currentAllIds" }) })
public class MyScoreMainAction extends PagingUtil<TScore> {
	private static final long serialVersionUID = 7726461249338915850L;
	private String currentPageIds = null;
	private String currentAllIds = null;
	private String flagString = null;
	private String information = null;
	{
		setClass(TScore.class, "score");
		setPageableAmount(10);
		setOrderSql(" order by score.id desc");
	}

	@Action(value = "toMain")
	public String toMain() {
		clearSession();
		return "toMain";
	}

	@Action(value = "toList")
	public String toList() {
		searchScore();
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

	private void searchScore() {
		 TStudent student = (TStudent)getSession().get(SystemConstant.CURRENTUSER);
		if ("1".equals(flagString)) {
			setSQL(" and score.type.name like ? ");
			queryParameters.add("%" + information + "%");
		} else if (flagString!=null&&!"1".equals(flagString)) {
			setSQL(" and score.year_str='"+flagString+"'");
		}
		setSQL(" and score.sudentId="+student.getId());
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
		searchScore();
		return "toList";
	}

	@Action(value = "toPrevious")
	public String toPrevious() {
		previous();
		searchScore();
		return "toList";
	}

	@Action(value = "toLast")
	public String toLast() {
		last();
		searchScore();
		return "toList";
	}

	@Action(value = "toFirst")
	public String toFirst() {
		first();
		searchScore();
		return "toList";
	}

	@Action(value = "toPage")
	public String toPage() {
		page();
		searchScore();
		return "toList";
	}

	@Action(value = "toReload")
	public String toReload() {
		searchScore();
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
}
