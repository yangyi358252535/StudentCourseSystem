package com.StudentCourseSystem.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.StudentCourseSystem.Dao.ITeacherDao;
import com.StudentCourseSystem.Service.ITeacherService;
import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TTeacher;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {

	private ITeacherDao teacherDao;

	@Override
	public TTeacher login(String username, String password) {
		return teacherDao.login(username, password);
	}

	@Override
	public List<TAuthAndSourceInfo> getAuthAndSourceInfo(int authid) {
		return teacherDao.getAuthAndSourceInfo(authid);
	}

	@Override
	public TTeacher getTeacher(long userid) {
		return teacherDao.get(userid);
	}

	@Override
	public void modifyTeacher(TTeacher teacher) {
		teacherDao.modify(teacher);
	}

	@Override
	public void addTeacher(TTeacher teacher) {
		teacherDao.add(teacher);
	}

	@Override
	public Long getMaxId() {
		return teacherDao.getMaxId();
	}

	public ITeacherDao getTeacherDao() {
		return teacherDao;
	}
	@Resource
	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Override
	public List<TTeacher> getAllTeacher(long specialtyid) {
		return teacherDao.getAllTeacher(specialtyid);
	}

}
