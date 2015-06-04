package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TClass;

public interface IClaszService {
	public TClass getClasz(long id);

	public void addClasz(TClass clasz);

	public void modifyClasz(TClass clasz);

	public Long getMaxId();

	public List<TClass> getAllTheClazz();
}