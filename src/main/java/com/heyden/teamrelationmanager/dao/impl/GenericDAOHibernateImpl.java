package com.heyden.teamrelationmanager.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.heyden.teamrelationmanager.dao.GenericDAO;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDAOHibernateImpl  <T, PK extends Serializable> implements GenericDAO<T, PK> {

	private static final String ORDER_BY_PATTERN =" ORDER BY %s";
	private static final String QUERY_ALL_PATTERN ="FROM %s%s";
	
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> type;
	
	public GenericDAOHibernateImpl() {}
	
	public void setClazz(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public void save(T dataObject) {
		getSession().saveOrUpdate(dataObject);
	}
	
	@Override
	public void update(T dataObject) {
		save(dataObject);
	}

	@Override
	public void create(T dataObject) {
		save(dataObject);
	}

	@Override
	public T get(PK id) {
		return getSession().get(type, id);
	}

	@Override
	public void delete(T dataObject) {
		getSession().delete(dataObject);
	}

	@Override
	public List<T> getAll() {
		return getAllOrderBy(null);
	}

	@Override
	public List<T> getAllOrderBy(String columnName) {
		String orderBy = "";
		if (StringUtils.isNotBlank(columnName)) {
			orderBy = String.format(ORDER_BY_PATTERN, columnName);
		}
		
		String hqlQuery = String.format(QUERY_ALL_PATTERN, type.getName(), orderBy);
		return getSession().createQuery(hqlQuery).list();
	}
	
	@Override
	public void deleteById(PK id) {
		T entity = get(id);
		delete(entity);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
