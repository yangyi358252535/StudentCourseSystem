package com.StudentCourseSystem.Dao.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.StudentCourseSystem.Dao.IScoreDao;
import com.StudentCourseSystem.bean.TMaster;
import com.StudentCourseSystem.bean.TScore;
@Component("scoreDao")
public class ScoreDaoImpl implements IScoreDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public TScore get(long id) {
		return hibernateTemplate.get(TScore.class, id);
	}

	@Override
	public void add(TScore score) {
		hibernateTemplate.save(score);
	}

	@Override
	public void modify(TScore score) {
		hibernateTemplate.update(score);
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
								.createQuery("select max(u.id) from TScore u ");
						return query.list();
					}
				});
		Long d = (Long) list.get(0);
		return d;
	}

	@Override
	public TMaster getTheMasterById(long id) {
		return hibernateTemplate.get(TMaster.class, id);
	}

	@Override
	public void updateStudentIdForScore(long studentId, long scoreId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery query = null;
		query = session.createSQLQuery("update t_score t set t.student_id="+studentId+" where t.id="+scoreId);
		query.executeUpdate();
	}
}
