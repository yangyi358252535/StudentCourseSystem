package com.StudentCourseSystem.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.StudentCourseSystem.Dao.ISpecialtyDao;
import com.StudentCourseSystem.Service.ISpecialtyService;
import com.StudentCourseSystem.bean.TSpecialty;

@Service("specialtyService")
public class SpecialtyServiceImpl implements ISpecialtyService {

	private ISpecialtyDao specialtyDao;

	@Override
	public TSpecialty getSpecialty(long id) {
		return specialtyDao.get(id);
	}

	@Override
	public void addSpecialty(TSpecialty specialty) {
		specialtyDao.add(specialty);
	}

	@Override
	public void modifySpecialty(TSpecialty specialty) {
		specialtyDao.modify(specialty);
	}

	@Override
	public Long getMaxId() {
		return specialtyDao.getMaxId();
	}

	public ISpecialtyDao getSpecialtyDao() {
		return specialtyDao;
	}

	@Resource
	public void setSpecialtyDao(ISpecialtyDao specialtyDao) {
		this.specialtyDao = specialtyDao;
	}

	@Override
	public List<TSpecialty> getAllSpecialty() {
		return specialtyDao.getAllSpecialty();
	}
}
