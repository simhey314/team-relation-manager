package com.heyden.teamrelationmanager.dao.impl;

import java.io.Serializable;
import java.util.List;

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

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> type;
	
	public GenericDAOHibernateImpl() {}
	
	public void setCLazz(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public void save(T dataObject) {
		getSession().saveOrUpdate(dataObject);
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
		return getSession().createQuery( "from " + type.getName() ).list();
	}

	@Override
	public void deleteById(PK id) {
		T entity = get(id);
		delete(entity);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
