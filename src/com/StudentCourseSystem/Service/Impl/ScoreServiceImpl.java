package com.StudentCourseSystem.Service.Impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.StudentCourseSystem.Dao.IScoreDao;
import com.StudentCourseSystem.Service.IScoreService;
import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TScore;

@Service("scoreService")
public class ScoreServiceImpl implements IScoreService {

	private IScoreDao scoreDao;
	@Override
	public TScore getScore(long id) {
		return scoreDao.get(id);
	}

	@Override
	public void addScore(TScore score) {
		scoreDao.add(score);
	}

	@Override
	public void modifyScore(TScore score) {
		scoreDao.modify(score);
	}

	@Override
	public Long getMaxId() {
		return scoreDao.getMaxId();
	}

	public IScoreDao getScoreDao() {
		return scoreDao;
	}
	@Resource
	public void setScoreDao(IScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Override
	public TMaster getTheMasterById(long id) {
		return scoreDao.getTheMasterById(id);
	}

	@Override
	public void updateStudentIdForScore(long studentId, long scoreId) {
		scoreDao.updateStudentIdForScore(studentId, scoreId);	
	}
}
