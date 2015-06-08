package com.StudentCourseSystem.Dao;

import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TScore;



public interface IScoreDao {
	public TScore get(long id);
	public void add(TScore score);
	public void modify(TScore score);
	public Long getMaxId();
	public TMaster getTheMasterById(long id);
	public void updateStudentIdForScore(long studentId,long scoreId);
}
