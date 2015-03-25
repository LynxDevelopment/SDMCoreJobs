package com.lynspa.sdm.jobs.bloomberg.load.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.lynspa.sdm.entities.job.SDMRow;

public class SDMRowDAO extends SDMAbstractDAO<SDMRow> {
	
	private SessionFactory sessionFactory;
	private HibernateTemplate template;
	
	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return template;
	}

	public SDMRowDAO() {
		super(SDMRow.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		this.template = new HibernateTemplate(this.sessionFactory);
		this.template.setCheckWriteOperations(false);
	}
}
