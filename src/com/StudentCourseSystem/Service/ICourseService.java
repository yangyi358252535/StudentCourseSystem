package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TCourse;

public interface ICourseService {

	public TCourse getCourse(long id);

	public void addCourse(TCourse course);

	public void modifyCourse(TCourse course);

	public Long getMaxId();

	public List<TCourse> getCourseBySpecialty(long sid,String year_str,long itemid);

}