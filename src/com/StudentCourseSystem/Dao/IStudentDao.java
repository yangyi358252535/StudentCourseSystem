package com.StudentCourseSystem.Dao;

import java.util.List;

import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TStudent;

public interface IStudentDao {
	public TStudent get(long id);

	public void add(TStudent student);

	public void modify(TStudent student);

	public Long getMaxId();

	public TStudent login(String username, String password);

	public List<TMaster> getMasterListByCode(String code);

	public List<TStudent> getStudentList(long classId);
}
