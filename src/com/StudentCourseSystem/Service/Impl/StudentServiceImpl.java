package com.StudentCourseSystem.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.StudentCourseSystem.Dao.IStudentDao;
import com.StudentCourseSystem.Service.IStudentService;
import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TStudent;

@Service("studentService")
public class StudentServiceImpl implements IStudentService {

	private IStudentDao studentDao;

	@Override
	public TStudent getStudent(long id) {
		return studentDao.get(id);
	}

	@Override
	public void addStudent(TStudent student) {
		studentDao.add(student);
	}

	@Override
	public void modifyStudent(TStudent student) {
		studentDao.modify(student);
	}

	@Override
	public Long getMaxId() {
		return studentDao.getMaxId();
	}

	@Override
	public TStudent login(String username, String password) {
		return studentDao.login(username, password);
	}

	@Override
	public List<TMaster> getMasterListByCode(String code) {
		return studentDao.getMasterListByCode(code);
	}

	@Override
	public List<TStudent> getStudentList(long classId) {
		return studentDao.getStudentList(classId);
	}

	public IStudentDao getStudentDao() {
		return studentDao;
	}

	@Resource
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

}
