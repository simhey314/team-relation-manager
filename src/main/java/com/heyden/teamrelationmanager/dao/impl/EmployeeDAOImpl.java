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

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.heyden.teamrelationmanager.dao.EmployeeDAO;
import com.heyden.teamrelationmanager.entity.Employee;

@Repository
class EmployeeDAOImpl extends GenericDAOHibernateImpl<Employee, Integer> implements EmployeeDAO {

	public EmployeeDAOImpl() {
		setClazz(Employee.class);
	}

	@Override
	public List<Employee> searchEmployee(final String searchValue) {
		Session session = getSession();

		List<Employee> employees = null;
		if (searchValue != null && searchValue.trim().length() > 0) {
			String employeeName = searchValue.trim().toLowerCase();
			Query<Employee> query = session.createQuery("FROM Employee WHERE LOWER(firstName) LIKE :EmployeeName OR LOWER(lastName) like :EmployeeName", Employee.class);
			query.setParameter("EmployeeName", "%" + employeeName + "%");
			employees = query.getResultList();
		} else {
			employees = getAll();
		}
		return employees;
	}

	@Override
	public void save(final Employee dataObject) {
		// @TODO: avoid that the select form null value do a blank team injection to employee
		if (dataObject.getTeam() != null && dataObject.getTeam().getId() <= 0) {
			dataObject.setTeam(null);
		}
		super.save(dataObject);
	}
}
