package com.StudentCourseSystem.action.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.StudentCourseSystem.Service.IClaszService;
import com.StudentCourseSystem.Service.IScoreService;
import com.StudentCourseSystem.Service.IStudentService;
import com.StudentCourseSystem.Service.ITeacherService;
import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TScore;
import com.StudentCourseSystem.bean.TStudent;
import com.StudentCourseSystem.bean.TTeacher;
import com.StudentCourseSystem.tool.BaseProcessAction;
import com.StudentCourseSystem.tool.SystemConstant;
import com.StudentCourseSystem.tool.SystemUtil;

@Controller
@Scope("prototype")
@ParentPackage("main")
@Namespace("/business/score")
public class StudentScoreProcessAction extends BaseProcessAction {
	private static final long serialVersionUID = -7518344973574116467L;
	private String username;
	private String password;
	private String newPass;
	private String oldPass;
	private String passflag;
	private IScoreService scoreService;
	private IStudentService studentService;
	private ITeacherService teacherService;
	private IClaszService claszService;
	private TStudent student = new TStudent();
	private String allIds = null;
	private long[] id;
	private float[] score;
	private String year_str;
	private long[] itemid;
	private String message = "0";
	private long detailId;
	@Action(value = "toLogin", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"includeProperties", "message", "excludeNullProperties", "true",
			"excludeProperties", "" }) })
	public String login() {
		Object currentUser = getCurrentUser();
		if (currentUser != null) {
			message = "用户已经登录，请注销或关闭浏览器或新开SESSION重新登录";
		} else {
			TStudent student = studentService.login(username, password);
			List<Map<String, List<TAuthAndSourceInfo>>> menuAndAuthInfoList = null;
			Map<String, List<TAuthAndSourceInfo>> map_AuthAndSourceInfo = null;
			if (student != null) {
				setCurrentUser(student);
				menuAndAuthInfoList = new ArrayList<Map<String, List<TAuthAndSourceInfo>>>();
				List<TAuthAndSourceInfo> authAndSourceInfoList = null;
				authAndSourceInfoList = teacherService.getAuthAndSourceInfo(3);
				if (authAndSourceInfoList != null) {
					map_AuthAndSourceInfo = new HashMap<String, List<TAuthAndSourceInfo>>();
					map_AuthAndSourceInfo.put(authAndSourceInfoList.get(0)
							.getMenuTitle_Name(), authAndSourceInfoList);
					menuAndAuthInfoList.add(map_AuthAndSourceInfo);
				}
				getSession().put("currentUserAuth", "学生");
				getSession().put("currentClass", student.getClasz().getName());
				getSession().put("currentUserCode", 2);
				getSession().put("BO_MenuAndAuthInfoList", menuAndAuthInfoList);
				message = "success";
			} else {
				message = "-1";
			}
		}
		return SUCCESS;
	}
	@Action(value = "logout",
			params={"contentType","text/html;charset=UTF-8"},
			results={@Result(name = "success",type="json", params = {
					"excludeNullProperties", "true","excludeProperties","" })
	})	
	public String logout(){
		setCurrentUser(null);
		getSession().put("BO_MenuAndAuthInfoList", null);
		getSession().put("currentUserCode", null);
		getSession().put("currentUserAuth", null);
		return SUCCESS;
	}
	@Action(value = "checkOldPass",
			params={"contentType","text/html;charset=UTF-8"},
			results={@Result(name = "success",type="json", params = {
					"includeProperties", "passflag",
					"excludeNullProperties", "true","excludeProperties","" })
	})	
	public String checkOldPass(){
		TStudent udr=(TStudent)getCurrentUser();
		if(!oldPass.equals(udr.getPassword())){
			passflag="0";
		}else{
			passflag="1";
		}
		return SUCCESS;
	}
	@Action(value = "changePass",
			params={"contentType","text/html;charset=UTF-8"},
			results={@Result(name = "success",type="json", params = {
					"excludeNullProperties", "true","excludeProperties","" })
	})	
	public String change() {
		TStudent employee=(TStudent)getCurrentUser();
		employee=studentService.getStudent(employee.getId());
		employee.setPassword(newPass);
		studentService.modifyStudent(employee);
		setCurrentUser(employee);
		return SUCCESS;
	}
	@Action(value = "addProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String add() {
		String createDate = SystemUtil.getSystemDateTime("yyyy-MM-dd HH:mm");
		Set<TScore> scores = new HashSet<TScore>();
		TTeacher teacher = (TTeacher) getSession().get(
				SystemConstant.CURRENTUSER);
		teacher = teacherService.getTeacher(teacher.getId());
		Long maxid = studentService.getMaxId();
		String userno = null;
		if (maxid != null) {
			student.setId(maxid + 1);
		} else {
			student.setId(1);
		}
		userno = SystemUtil.getUserNo("ST");
		student.setNum(userno);
		student.setCreateDate(createDate);
		student.setPassword(SystemConstant.PASSWORD);
		student.setScores(scores);
		studentService.addStudent(student);
		return SUCCESS;
	}


	@Action(value = "scoreProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String score() {
		TScore scoreT = null;
		for (int i = 0; i < id.length; i++) {
			scoreT = scoreService.getScore(id[i]);
			scoreT.setScore(score[i]);
			scoreT.setDeleteflag(0);
//			scoreT.setType(type);
			scoreT.setCreateDate(SystemUtil
					.getSystemDateTime(" yyyy-MM-dd HH:mm"));
			scoreService.modifyScore(scoreT);
		}
		return SUCCESS;
	}

	@Action(value = "modifyProcess", params = { "contentType",
			"text/html;charset=UTF-8" }, results = { @Result(name = "success", type = "json", params = {
			"excludeNullProperties", "true", "excludeProperties", "" }) })
	public String modify() {
		String name=student.getName();
		String sex=student.getSex();
		String tel=student.getTel();
		student=studentService.getStudent(student.getId());
		student.setName(name);
		student.setSex(sex);
		student.setTel(tel);
		studentService.modifyStudent(student);
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
	public IClaszService getClaszService() {
		return claszService;
	}

	@Resource
	public void setClaszService(IClaszService claszService) {
		this.claszService = claszService;
	}

	@JSON(serialize = false)
	public ITeacherService getTeacherService() {
		return teacherService;
	}

	@Resource
	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
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

	@JSON(serialize = false)
	public long[] getItemid() {
		return itemid;
	}

	public void setItemid(long[] itemid) {
		this.itemid = itemid;
	}

	@JSON(serialize = false)
	public String getYear_str() {
		return year_str;
	}

	public void setYear_str(String year_str) {
		this.year_str = year_str;
	}
	@JSON(serialize = false)
	public long getDetailId() {
		return detailId;
	}

	public void setDetailId(long detailId) {
		this.detailId = detailId;
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
