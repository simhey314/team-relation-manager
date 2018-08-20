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
	public void saveEmployee(Employee Employee) {
		employeeDAO.save(Employee);
	}

	@Override
	@Transactional
	public Employee getEmployee(int id) {
		return employeeDAO.get(id);
	}

	@Override
	@Transactional
	public void deleteEmployee(int id) {
		employeeDAO.deleteById(id);		
	}

	@Override
	@Transactional
	public List<Employee> searchEmployee(String searchName) {
		return employeeDAO.searchEmployee(searchName);
	}

}
