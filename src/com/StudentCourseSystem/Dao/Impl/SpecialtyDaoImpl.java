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

import com.StudentCourseSystem.Dao.ISpecialtyDao;
import com.StudentCourseSystem.bean.TSpecialty;

@Component("specialtyDao")
public class SpecialtyDaoImpl implements ISpecialtyDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public TSpecialty get(long id) {
		return hibernateTemplate.get(TSpecialty.class, id);
	}

	@Override
	public void add(TSpecialty specialty) {
		hibernateTemplate.save(specialty);
	}

	@Override
	public void modify(TSpecialty specialty) {
		hibernateTemplate.update(specialty);
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
								.createQuery("select max(u.id) from TSpecialty u ");
						return query.list();
					}
				});
		Long d = (Long) list.get(0);
		return d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TSpecialty> getAllSpecialty() {
		return hibernateTemplate.find("from TSpecialty");
	}

}
