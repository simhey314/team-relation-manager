package com.heyden.teamrelationmanager.dao;

import java.util.List;

import com.heyden.teamrelationmanager.entity.Employee;

public interface EmployeeDAO extends GenericDAO<Employee, Integer> {

	public List<Employee> searchEmployee(String searchName);
}
