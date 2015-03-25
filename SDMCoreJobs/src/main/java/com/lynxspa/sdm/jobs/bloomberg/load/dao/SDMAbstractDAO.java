/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lynxspa.sdm.jobs.bloomberg.load.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class SDMAbstractDAO<T> {
	private Class<T> entityClass;

	public SDMAbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract HibernateTemplate getHibernateTemplate();

	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(entityClass);
	}

	@Transactional
	public void create(T entity) {
		System.out.println("Creating: " + entity.toString());
		getHibernateTemplate().save(entity);
	}
	
	@Transactional
	public void insert(T entity) {
		System.out.println("Creating: " + entity.toString());
		getHibernateTemplate().save(entity);
	}

	@Transactional
	public void edit(T entity) {
		//getHibernateTemplate().merge(entity);
		getHibernateTemplate().update(entity);
	}

	@Transactional
	public void delete(T entity) {
		getHibernateTemplate().delete(getHibernateTemplate().merge(entity));
		System.out.println("Deleted " + entity.toString());
	}

	// public T find(Object id) {
	// return getHibernateTemplate().find(entityClass, id);
	// }

	@Transactional
	public T findById(long id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	@Transactional
	public List<T> findByExample(T entity) {
		return (List<T>) getHibernateTemplate().findByExample(entity);
	}

	@Transactional
	public List<T> findByExample(T entity, int firstResult, int maxResults) {
		List<T> resultList = getHibernateTemplate().findByExample(entity,
				firstResult, maxResults);
		return resultList;
	}

	@Transactional
	// ("reportFieldName", "AIFName")
	public List<T> findAllByProperty(String propertyName, Object value) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq(propertyName, value));
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}

	@Transactional
	public List<T> findAllByProperty(List<Map> list) throws Exception {

		DetachedCriteria criteria = createDetachedCriteria();
		for (int i = 0; i < list.size(); i++) {

			Map<String, Object> map = (Map<String, Object>) list.get(i);
			String propertyName = (String) map.get("propertyName");
			Object value = map.get("value");

			// logger.debug("propertyName "+ propertyName + "value "+ value
			// +" count "+list.size());
			System.out.println("propertyName " + propertyName + "value "
					+ value + " count " + list.size());

			criteria.add(Restrictions.eq(propertyName, value));

		}
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}

	@Transactional
	public List<T> findByQuery(final String queryString) {
		return (List<T>) getHibernateTemplate().find(queryString);
	}

	// @Transactional
	// public List<T> findAll() {
	// return (List<T>) getHibernateTemplate().findByCriteria(
	// DetachedCriteria.forClass(entityClass).add(
	// Example.create(entityClass)));
	// }

	@Transactional
	public List<T> findAll() {
		return (List<T>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entityClass));
	}

	@Transactional
	public void deleteAll() {
		List<T> entities = findAll();
		for (T entity : entities) {
			delete(entity);
		}
	}
	
//	@Transactional
//	public void disable(T entity) {
//		entity.getAuditor().setDeleted(true);
//		getHibernateTemplate().update(entity);
//	}

	// TODO:RT see if we have to add more methods
	
	// public List<T> findRange(int[] range) {
	// javax.persistence.criteria.CriteriaQuery cq =
	// getHibernateTemplate().getCriteriaBuilder().createQuery();
	// cq.select(cq.from(entityClass));
	// javax.persistence.Query q = getHibernateTemplate().createQuery(cq);
	// q.setMaxResults(range[1] - range[0]);
	// q.setFirstResult(range[0]);
	// return q.getResultList();
	// }
	//
	// public int count() {
	// javax.persistence.criteria.CriteriaQuery cq =
	// getHibernateTemplate().getCriteriaBuilder().createQuery();
	// javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
	// cq.select(getHibernateTemplate().getCriteriaBuilder().count(rt));
	// javax.persistence.Query q = getHibernateTemplate().createQuery(cq);
	// return ((Long) q.getSingleResult()).intValue();
	// }

}
