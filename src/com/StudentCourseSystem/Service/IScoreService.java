package com.StudentCourseSystem.Service;

import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TScore;



public interface IScoreService {
	public TScore getScore(long id);
	public void addScore(TScore score);
	public void modifyScore(TScore score);
	public Long getMaxId();
	public TMaster getTheMasterById(long id);
	public void updateStudentIdForScore(long studentId, long scoreId);
}
