package com.StudentCourseSystem.Dao;

import java.util.List;

import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TTeacher;

public interface ITeacherDao {
	public TTeacher login(String username, String password);

	public List<TAuthAndSourceInfo> getAuthAndSourceInfo(int authid);

	public TTeacher get(long userid);

	public void modify(TTeacher teacher);

	public void add(TTeacher teacher);

	public Long getMaxId();
	
	public List<TTeacher> getAllTeacher(long specialtyid);
}