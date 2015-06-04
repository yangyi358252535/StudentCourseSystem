package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TStudent;

public interface IStudentService {
	public TStudent getStudent(long id);

	public void addStudent(TStudent student);

	public void modifyStudent(TStudent student);

	public Long getMaxId();

	public TStudent login(String username, String password);

	public List<TMaster> getMasterListByCode(String code);

	public List<TStudent> getStudentList(long classId);
}