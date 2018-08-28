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
package com.heyden.teamrelationmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heyden.teamrelationmanager.dao.EmployeeDAO;
import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.service.EmployeeService;

@Service
class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDAO.getAll();
	}

	@Override
	@Transactional
	public void saveEmployee(final Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	@Transactional
	public Employee getEmployee(final int id) {
		return employeeDAO.get(id);
	}

	@Override
	@Transactional
	public void deleteEmployee(final int id) {
		employeeDAO.deleteById(id);
	}

	@Override
	@Transactional
	public List<Employee> searchEmployee(final String searchName) {
		return employeeDAO.searchEmployee(searchName);
	}

	@Override
	@Transactional
	public List<Employee> getEmployees(final String orderByColumn) {
		return employeeDAO.getAllOrderBy(orderByColumn);
	}

}
