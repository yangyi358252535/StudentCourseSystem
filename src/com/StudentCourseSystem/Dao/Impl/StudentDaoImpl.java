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

import com.StudentCourseSystem.Dao.IStudentDao;
import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TStudent;

@Component("studentDao")
public class StudentDaoImpl implements IStudentDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public TStudent get(long id) {
		return hibernateTemplate.get(TStudent.class, id);
	}

	@Override
	public void add(TStudent student) {
		hibernateTemplate.save(student);
	}

	@Override
	public void modify(TStudent student) {
		hibernateTemplate.update(student);
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
								.createQuery("select max(u.id) from TStudent u ");
						return query.list();
					}
				});
		Long d = (Long) list.get(0);
		return d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TStudent login(final String username, final String password) {
		List<TStudent> list = hibernateTemplate
				.executeFind(new HibernateCallback<List<TStudent>>() {
					public List<TStudent> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("from TStudent t where t.num=:username and t.password=:password ");
						query.setParameter("username", username);
						query.setParameter("password", password);
						List<TStudent> list1 = query.list();
						return list1;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TMaster> getMasterListByCode(final String code) {
		List<TMaster> list = hibernateTemplate
				.executeFind(new HibernateCallback<List<TMaster>>() {
					public List<TMaster> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("from TMaster t where t.code='"
										+ code + "'");
						List<TMaster> list1 = query.list();
						return list1;
					}
				});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TStudent> getStudentList(long classId) {
		return hibernateTemplate
				.find("from TStudent where clasz.id=" + classId);
	}
}
