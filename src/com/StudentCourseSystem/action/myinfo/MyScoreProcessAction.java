package com.StudentCourseSystem.action.myinfo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.StudentCourseSystem.tool.BaseProcessAction;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/myinfo/score")
public class MyScoreProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = 3741281666658972089L;
	private String allIds = null;
	private String message = "0";

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

}
