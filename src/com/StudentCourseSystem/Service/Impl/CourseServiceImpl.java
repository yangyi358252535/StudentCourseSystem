package com.StudentCourseSystem.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.StudentCourseSystem.Dao.ICourseDao;
import com.StudentCourseSystem.Service.ICourseService;
import com.StudentCourseSystem.bean.TCourse;

@Service("courseService")
public class CourseServiceImpl implements ICourseService {

	private ICourseDao courseDao;

	@Override
	public TCourse getCourse(long id) {
		return courseDao.get(id);
	}

	@Override
	public void addCourse(TCourse course) {
		courseDao.add(course);
	}

	@Override
	public void modifyCourse(TCourse course) {
		courseDao.modify(course);
	}

	@Override
	public Long getMaxId() {
		return courseDao.getMaxId();
	}

	@Override
	public List<TCourse> getCourseByTeacher(long tid) {
		return courseDao.getCourseByTeacher(tid);
	}

	@Override
	public List<TCourse> getCurrentCourse(long specialtyid, long jieshuid,
			long weizhiid) {
		return courseDao.getCurrentCourse(specialtyid, jieshuid, weizhiid);
	}

	public ICourseDao getCourseDao() {
		return courseDao;
	}

	@Resource
	public void setCourseDao(ICourseDao courseDao) {
		this.courseDao = courseDao;
	}

}