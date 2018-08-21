package com.heyden.teamrelationmanager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heyden.teamrelationmanager.dao.EmployeeDAO;
import com.heyden.teamrelationmanager.entity.Employee;

@Repository
class EmployeeDAOImpl extends GenericDAOHibernateImpl<Employee, Integer> implements EmployeeDAO {

	public EmployeeDAOImpl() {
		setClazz(Employee.class);
	}
	
	@Override
	public List<Employee> searchEmployee(String searchValue) {
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
}
