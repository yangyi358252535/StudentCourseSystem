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

import com.StudentCourseSystem.Service.IManagerService;
import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TManager;
import com.StudentCourseSystem.tool.BaseProcessAction;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/system/manager")
public class ManagerProcessAction extends BaseProcessAction {

	private static final long serialVersionUID = -4354839434458766459L;
	private String username;
	private String password;
	private String newPass;
	private String oldPass;
	private String passflag;
	private TManager manager;
	private IManagerService managerService;
	private String allIds = null;
	private String message = "0";

	@Action(value = "toLogin", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"includeProperties", "message", "excludeNullProperties", "true",
			"excludeProperties", "" }) })
	public String login() {
		Object currentUser = getCurrentUser();
		if (currentUser != null) {
			message = "用户已经登录，请注销或关闭浏览器或新开SESSION重新登录";
		} else {
			TManager manager = managerService.login(username, password);
			List<Map<String, List<TAuthAndSourceInfo>>> menuAndAuthInfoList = null;
			Map<String, List<TAuthAndSourceInfo>> map_AuthAndSourceInfo = null;
			if (manager != null) {
				setCurrentUser(manager);
				menuAndAuthInfoList = new ArrayList<Map<String, List<TAuthAndSourceInfo>>>();
				List<TAuthAndSourceInfo> authAndSourceInfoList = null;
				for(int i=1;i<=2;i++){
					authAndSourceInfoList=managerService.getAuthAndSourceInfo(i);
					if(authAndSourceInfoList!=null){
						map_AuthAndSourceInfo=new HashMap<String, List<TAuthAndSourceInfo>>();
						map_AuthAndSourceInfo.put(authAndSourceInfoList.get(0).getMenuTitle_Name(), authAndSourceInfoList);
						menuAndAuthInfoList.add(map_AuthAndSourceInfo);
					}
				}
				getSession().put("currentUserAuth", "系统管理员");
				getSession().put("currentUserCode", 0);
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
		TManager udr = (TManager) getCurrentUser();
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
		TManager employee = (TManager) getCurrentUser();
		employee = managerService.getManager(employee.getId());
		employee.setPassword(newPass);
		managerService.modifyManager(employee);
		setCurrentUser(employee);
		return SUCCESS;
	}

	@Action(value = "clearSessionForSearch", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "clearSesssion", type = "json") })
	public String clearSessionForSearch() {
		clearSession();
		return "clearSesssion";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public TManager getManager() {
		return manager;
	}

	public void setManager(TManager manager) {
		this.manager = manager;
	}

	@JSON(serialize = false)
	public IManagerService getManagerService() {
		return managerService;
	}

	@Resource
	public void setManagerService(IManagerService managerService) {
		this.managerService = managerService;
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
}
