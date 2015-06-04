package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TSpecialty;

public interface ISpecialtyService {
	public TSpecialty getSpecialty(long id);

	public void addSpecialty(TSpecialty specialty);

	public void modifySpecialty(TSpecialty specialty);

	public Long getMaxId();
	
	public List<TSpecialty> getAllSpecialty();
}