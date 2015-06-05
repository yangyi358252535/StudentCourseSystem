package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TInstitute;


public interface IInstituteService {
	public TInstitute getInstitute(long id);

	public void addInstitute(TInstitute institute);

	public void modifyInstitute(TInstitute institute);

	public Long getMaxId();

	public List<TInstitute> getAllTheInstitute();
}