package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TTeacher;

public interface ITeacherService {
	public TTeacher login(String username, String password);

	public List<TAuthAndSourceInfo> getAuthAndSourceInfo(int authid);

	public TTeacher getTeacher(long userid);

	public void modifyTeacher(TTeacher teacher);

	public void addTeacher(TTeacher teacher);

	public Long getMaxId();
	public List<TTeacher> getAllTeacher(long specialtyid);
}