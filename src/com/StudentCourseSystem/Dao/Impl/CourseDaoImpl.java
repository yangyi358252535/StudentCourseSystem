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

import com.StudentCourseSystem.Dao.ICourseDao;
import com.StudentCourseSystem.bean.TCourse;

@Component("courseDao")
public class CourseDaoImpl implements ICourseDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public TCourse get(long id) {
		return hibernateTemplate.get(TCourse.class, id);
	}

	@Override
	public void add(TCourse course) {
		hibernateTemplate.save(course);
	}

	@Override
	public void modify(TCourse course) {
		hibernateTemplate.update(course);
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
								.createQuery("select max(u.id) from TCourse u ");
						return query.list();
					}
				});
		Long d = (Long) list.get(0);
		return d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TCourse> getCourseByTeacher(long tid) {
		if(tid==0){
			return hibernateTemplate.find("from TCourse t");
		}else{
			return hibernateTemplate.find("from TCourse t where t.teacher.id="
					+ tid);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TCourse> getCurrentCourse(long specialtyid, long jieshuid,
			long weizhiid) {
		return hibernateTemplate.find("from TCourse t where t.specialty.id="
				+ specialtyid + " and  t.jieshu.masterid=" + jieshuid
				+ " and t.weizhi.masterid=" + weizhiid);
	}

}
