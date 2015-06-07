package com.StudentCourseSystem.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.StudentCourseSystem.Dao.IInstituteDao;
import com.StudentCourseSystem.Service.IInstituteService;
import com.StudentCourseSystem.bean.TInstitute;
@Service("instituteService")
public class InstituteServiceImpl implements IInstituteService {

	private IInstituteDao instituteDao;
	@Override
	public TInstitute getInstitute(long id) {
		return instituteDao.get(id);
	}

	@Override
	public void addInstitute(TInstitute institute) {
		instituteDao.add(institute);
	}

	@Override
	public void modifyInstitute(TInstitute institute) {
		instituteDao.modify(institute);
	}

	@Override
	public Long getMaxId() {
		return instituteDao.getMaxId();
	}

	@Override
	public List<TInstitute> getAllTheInstitute() {
		return instituteDao.getAllTheInstitute();
	}

	public IInstituteDao getInstituteDao() {
		return instituteDao;
	}
	@Resource
	public void setInstituteDao(IInstituteDao instituteDao) {
		this.instituteDao = instituteDao;
	}

}
