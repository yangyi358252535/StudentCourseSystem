package com.StudentCourseSystem.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.StudentCourseSystem.Service.IClaszService;
import com.StudentCourseSystem.Service.ITeacherService;
import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TTeacher;
import com.StudentCourseSystem.tool.BaseProcessAction;
import com.StudentCourseSystem.tool.SystemConstant;
import com.StudentCourseSystem.tool.SystemUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/teacher")
public class TeacherProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = 8511180782629497377L;
	private TTeacher teacher=new TTeacher();
	private String username;
	private String password;
	private String newPass;
	private String oldPass;
	private String passflag;
	private ITeacherService teacherService;
	private IClaszService claszService;
	private long oldClassId;
	private String allIds = null;
	private String message = "0";

	@Action(value = "toLogin", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"includeProperties", "message", "excludeNullProperties", "true",
			"excludeProperties", "" }) })
	public String login() {
		Object currentUser = getCurrentUser();
		if (currentUser != null) {
			message = "";
		} else {
			TTeacher teacher = teacherService.login(username, password);
			List<Map<String, List<TAuthAndSourceInfo>>> menuAndAuthInfoList = null;
			Map<String, List<TAuthAndSourceInfo>> map_AuthAndSourceInfo = null;
			if (teacher != null) {
				setCurrentUser(teacher);
				menuAndAuthInfoList = new ArrayList<Map<String, List<TAuthAndSourceInfo>>>();
				List<TAuthAndSourceInfo> authAndSourceInfoList = null;
				authAndSourceInfoList = teacherService.getAuthAndSourceInfo(2);
				if (authAndSourceInfoList != null) {
					map_AuthAndSourceInfo = new HashMap<String, List<TAuthAndSourceInfo>>();
					map_AuthAndSourceInfo.put(authAndSourceInfoList.get(0)
							.getMenuTitle_Name(), authAndSourceInfoList);
					menuAndAuthInfoList.add(map_AuthAndSourceInfo);
				}
				getSession().put("currentUserAuth", "教师");
				getSession().put("currentUserCode", 1);
				getSession().put("BO_MenuAndAuthInfoList", menuAndAuthInfoList);
				message = "success";
			} else {
				message = "-1";
			}
		}
		return SUCCESS;
	}

	@Action(value = "logout", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String logout() {
		setCurrentUser(null);
		getSession().put("BO_MenuAndAuthInfoList", null);
		getSession().put("currentUserCode", null);
		getSession().put("currentUserAuth", null);
		return SUCCESS;
	}

	@Action(value = "checkOldPass", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"includeProperties", "passflag", "excludeNullProperties", "true",
			"excludeProperties", "" }) })
	public String checkOldPass() {
		TTeacher udr = (TTeacher) getCurrentUser();
		if (!oldPass.equals(udr.getPassword())) {
			passflag = "0";
		} else {
			passflag = "1";
		}
		return SUCCESS;
	}

	@Action(value = "changePass", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String change() {
		TTeacher employee = (TTeacher) getCurrentUser();
		employee = teacherService.getTeacher(employee.getId());
		employee.setPassword(newPass);
		teacherService.modifyTeacher(employee);
		setCurrentUser(employee);
		return SUCCESS;
	}

	@Action(value = "addProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String add() {
		Long maxid = teacherService.getMaxId();
		String userno = null;
		if (maxid != null) {
			teacher.setId(maxid + 1);
		} else {
			teacher.setId(1);
		}
		userno = SystemUtil.getUserNo("T");
		teacher.setNum(userno);
		teacher.setPassword(SystemConstant.PASSWORD);
		teacherService.addTeacher(teacher);
		return SUCCESS;
	}

	@Action(value = "modifyProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String modify() {
		teacherService.modifyTeacher(teacher);
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
	public TTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(TTeacher teacher) {
		this.teacher = teacher;
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
	public long getOldClassId() {
		return oldClassId;
	}

	public void setOldClassId(long oldClassId) {
		this.oldClassId = oldClassId;
	}

	@JSON(serialize = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JSON(serialize = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JSON(serialize = false)
	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@JSON(serialize = false)
	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getPassflag() {
		return passflag;
	}

	public void setPassflag(String passflag) {
		this.passflag = passflag;
	}
	@JSON(serialize = false)
	public ITeacherService getTeacherService() {
		return teacherService;
	}
	@Resource
	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
}
