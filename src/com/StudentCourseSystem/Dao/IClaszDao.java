package com.StudentCourseSystem.Dao;

import java.util.List;

import com.StudentCourseSystem.bean.TClass;

public interface IClaszDao {
	public TClass get(long id);

	public void add(TClass clasz);

	public void modify(TClass clasz);

	public Long getMaxId();

	public List<TClass> getAllTheClazz();
}
