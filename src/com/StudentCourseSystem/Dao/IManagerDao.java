package com.StudentCourseSystem.Dao;

import java.util.List;

import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TManager;

public interface IManagerDao {
	public TManager login(String username, String password);

	public List<TAuthAndSourceInfo> getAuthAndSourceInfo(int authid);

	public TManager get(long userid);

	public void modify(TManager u);

	public void add(TManager u);

	public Long getMaxId();
}