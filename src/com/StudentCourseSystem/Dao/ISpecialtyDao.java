package com.StudentCourseSystem.Dao;

import java.util.List;

import com.StudentCourseSystem.bean.TSpecialty;

public interface ISpecialtyDao {
	public TSpecialty get(long id);

	public void add(TSpecialty specialty);

	public void modify(TSpecialty specialty);

	public Long getMaxId();
	
	public List<TSpecialty> getAllSpecialty();
}