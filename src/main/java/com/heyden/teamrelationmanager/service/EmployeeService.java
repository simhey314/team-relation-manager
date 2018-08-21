package com.heyden.teamrelationmanager.service;

import java.util.List;

import com.heyden.teamrelationmanager.entity.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public List<Employee> getEmployees(String orderByColumn);
	
	public void saveEmployee(Employee Employee);

	public Employee getEmployee(int id);

	public void deleteEmployee(int id);

	public List<Employee> searchEmployee(String searchName);
}
