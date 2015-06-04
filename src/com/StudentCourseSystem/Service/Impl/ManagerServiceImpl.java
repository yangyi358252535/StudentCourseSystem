package com.StudentCourseSystem.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.StudentCourseSystem.Dao.IManagerDao;
import com.StudentCourseSystem.Service.IManagerService;
import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TManager;

@Service("managerService")
public class ManagerServiceImpl implements IManagerService {

	private IManagerDao managerDao;

	@Override
	public TManager login(String username, String password) {
		return managerDao.login(username, password);
	}

	@Override
	public List<TAuthAndSourceInfo> getAuthAndSourceInfo(int authid) {
		return managerDao.getAuthAndSourceInfo(authid);
	}

	@Override
	public TManager getManager(long userid) {
		return managerDao.get(userid);
	}

	@Override
	public void modifyManager(TManager u) {
		managerDao.modify(u);
	}

	@Override
	public void addManager(TManager u) {
		managerDao.add(u);
	}

	@Override
	public Long getMaxId() {
		return managerDao.getMaxId();
	}

	public IManagerDao getManagerDao() {
		return managerDao;
	}

	@Resource
	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao;
	}

}
