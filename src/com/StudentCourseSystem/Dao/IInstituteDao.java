package com.StudentCourseSystem.Dao;

import java.util.List;

import com.StudentCourseSystem.bean.TInstitute;


public interface IInstituteDao {
	public TInstitute get(long id);

	public void add(TInstitute institute);

	public void modify(TInstitute institute);

	public Long getMaxId();

	public List<TInstitute> getAllTheInstitute();
}
