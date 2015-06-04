package com.StudentCourseSystem.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.StudentCourseSystem.Dao.IClaszDao;
import com.StudentCourseSystem.Service.IClaszService;
import com.StudentCourseSystem.bean.TClass;

@Service("claszService")
public class ClaszServiceImpl implements IClaszService {

	private IClaszDao claszDao;

	@Override
	public TClass getClasz(long id) {
		return claszDao.get(id);
	}

	@Override
	public void addClasz(TClass clasz) {
		claszDao.add(clasz);
	}

	@Override
	public void modifyClasz(TClass clasz) {
		claszDao.modify(clasz);
	}

	@Override
	public Long getMaxId() {
		return claszDao.getMaxId();
	}

	@Override
	public List<TClass> getAllTheClazz() {
		return claszDao.getAllTheClazz();
	}

	public IClaszDao getClaszDao() {
		return claszDao;
	}

	@Resource
	public void setClaszDao(IClaszDao claszDao) {
		this.claszDao = claszDao;
	}
}
