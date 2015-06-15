package com.StudentCourseSystem.Dao;

import java.util.List;

import com.StudentCourseSystem.bean.TCourse;

public interface ICourseDao {

	public TCourse get(long id);

	public void add(TCourse course);

	public void modify(TCourse course);

	public Long getMaxId();

	public List<TCourse> getCourseBySpecialty(long sid,String year_str,long itemid);

}
