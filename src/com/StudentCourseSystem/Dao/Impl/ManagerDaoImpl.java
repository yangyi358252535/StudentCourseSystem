package com.StudentCourseSystem.Dao.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.StudentCourseSystem.Dao.IManagerDao;
import com.StudentCourseSystem.bean.TAuthAndSourceInfo;
import com.StudentCourseSystem.bean.TManager;

@Component("managerDao")
public class ManagerDaoImpl implements IManagerDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TManager login(final String username, final String password) {
		List<TManager> list = hibernateTemplate
				.executeFind(new HibernateCallback<List<TManager>>() {
					public List<TManager> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("from TManager t where t.num=:username and t.password=:password ");
						query.setParameter("username", username);
						query.setParameter("password", password);
						List<TManager> list1 = query.list();
						return list1;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TAuthAndSourceInfo> getAuthAndSourceInfo(final int authid) {
		List<TAuthAndSourceInfo> list = hibernateTemplate
				.executeFind(new HibernateCallback<List<TAuthAndSourceInfo>>() {
					public List<TAuthAndSourceInfo> doInHibernate(
							Session session) throws HibernateException,
							SQLException {
						Query query = session
								.createQuery("from TAuthAndSourceInfo t where t.authid=:authid");
						query.setParameter("authid", authid);
						List<TAuthAndSourceInfo> list1 = query.list();
						return list1;
					}
				});
		return list;
	}

	@Override
	public TManager get(long userid) {
		return hibernateTemplate.get(TManager.class, userid);
	}

	@Override
	public void modify(TManager u) {
		hibernateTemplate.update(u);
	}

	@Override
	public void add(TManager u) {
		hibernateTemplate.save(u);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Long getMaxId() {
		List list = hibernateTemplate
				.executeFind(new HibernateCallback<List>() {
					@Override
					public List doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select max(u.id) from TManager u ");
						return query.list();
					}
				});
		Long d = (Long) list.get(0);
		return d;
	}

}
