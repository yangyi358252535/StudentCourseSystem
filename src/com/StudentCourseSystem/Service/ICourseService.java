package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TCourse;

public interface ICourseService {

	public TCourse getCourse(long id);

	public void addCourse(TCourse course);

	public void modifyCourse(TCourse course);

	public Long getMaxId();

	public List<TCourse> getCourseByTeacher(long tid);

	public List<TCourse> getCurrentCourse(long specialtyid, long jieshuid,
			long weizhiid);
}