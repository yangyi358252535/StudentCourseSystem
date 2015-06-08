package com.StudentCourseSystem.tool;

import java.util.Comparator;

import com.StudentCourseSystem.bean.TScore;


public class ScoreComparable implements Comparator<TScore> {

	// 对象的排序方式[升、降]
	public boolean sortASC = false;

	@Override
	public int compare(TScore f1, TScore f2) {

		long result = 0;

		if (sortASC) {
			long id1 = f1.getId();
			long id2 = f2.getId();
			result =id1-id2;
		} else {
			long id1 = f1.getId();
			long id2 = f2.getId();
			result =id2-id1;
		}
		return Integer.parseInt(String.valueOf(result));
	}
}