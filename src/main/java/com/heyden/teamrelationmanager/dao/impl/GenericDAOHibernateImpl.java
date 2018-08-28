/**
 * Copyright 2018 Simon Heyden
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 **/

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
public class GenericDAOHibernateImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {

	private static final String ORDER_BY_PATTERN = " ORDER BY %s";
	private static final String QUERY_ALL_PATTERN = "FROM %s%s";

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> type;

	public GenericDAOHibernateImpl() {
	}

	public void setClazz(final Class<T> type) {
		this.type = type;
	}

	@Override
	public void save(final T dataObject) {
		getSession().saveOrUpdate(dataObject);
	}

	@Override
	public void update(final T dataObject) {
		save(dataObject);
	}

	@Override
	public void create(final T dataObject) {
		save(dataObject);
	}

	@Override
	public T get(final PK id) {
		return getSession().get(type, id);
	}

	@Override
	public void delete(final T dataObject) {
		getSession().delete(dataObject);
	}

	@Override
	public List<T> getAll() {
		return getAllOrderBy(null);
	}

	@Override
	public List<T> getAllOrderBy(final String columnName) {
		String orderBy = "";
		if (StringUtils.isNotBlank(columnName)) {
			orderBy = String.format(ORDER_BY_PATTERN, columnName);
		}

		String hqlQuery = String.format(QUERY_ALL_PATTERN, type.getName(), orderBy);
		return getSession().createQuery(hqlQuery, type).list();
	}

	@Override
	public void deleteById(final PK id) {
		T entity = get(id);
		if (entity == null) {
			return;
		}
		delete(entity);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
