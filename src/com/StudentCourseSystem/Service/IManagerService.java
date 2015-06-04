package com.StudentCourseSystem.Service;

import java.util.List;

import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TManager;

public interface IManagerService {
	public TManager login(String username, String password);

	public List<TAuthAndSourceInfo> getAuthAndSourceInfo(int authid);

	public TManager getManager(long userid);

	public void modifyManager(TManager u);

	public void addManager(TManager u);

	public Long getMaxId();
}