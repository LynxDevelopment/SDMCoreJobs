package com.lynspa.sdm.jobs.bloomberg.load.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.lynspa.sdm.entities.job.SDMJobType;

public class SDMJobTypeDAO extends SDMAbstractDAO<SDMJobType> {

	private SessionFactory sessionFactory;
	private HibernateTemplate template;
	
	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return template;
	}

	public SDMJobTypeDAO() {
		super(SDMJobType.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		this.template = new HibernateTemplate(this.sessionFactory);
		this.template.setCheckWriteOperations(false);
	}
}
