package com.lynspa.sdm.jobs.bloomberg.load.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.lynspa.sdm.entities.job.SDMJobField;

public class SDMJobFieldDAO extends SDMAbstractDAO<SDMJobField> {

	private SessionFactory sessionFactory;
	private HibernateTemplate template;
	
	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return template;
	}

	public SDMJobFieldDAO() {
		super(SDMJobField.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		this.template = new HibernateTemplate(this.sessionFactory);
		this.template.setCheckWriteOperations(false);
	}
}
