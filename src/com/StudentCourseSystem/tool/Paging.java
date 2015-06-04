package com.StudentCourseSystem.tool;

/**
 * @author yangyi
 * @date 2013-1-28 下午8:53:51
 */
public interface Paging {
	
	public boolean isFirstAvailiable();
	public boolean isLastAvailiable();
	public boolean isNextAvailiable();
	public boolean isPreviousAvailiable();
	public void first();
	public void last();
	public void next();
	public void page();
	public void previous();
}
