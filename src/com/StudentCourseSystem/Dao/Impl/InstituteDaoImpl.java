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

import com.StudentCourseSystem.Dao.IInstituteDao;
import com.StudentCourseSystem.bean.TInstitute;
@Component("instituteDao")
public class InstituteDaoImpl implements IInstituteDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public TInstitute get(long id) {
		return hibernateTemplate.get(TInstitute.class, id);
	}

	@Override
	public void add(TInstitute institute) {
		hibernateTemplate.save(institute);
	}

	@Override
	public void modify(TInstitute institute) {
		hibernateTemplate.update(institute);
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
								.createQuery("select max(u.id) from TInstitute u ");
						return query.list();
					}
				});
		Long d = (Long) list.get(0);
		return d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TInstitute> getAllTheInstitute() {
		return hibernateTemplate.find("from　TInstitute　");
	}

}
