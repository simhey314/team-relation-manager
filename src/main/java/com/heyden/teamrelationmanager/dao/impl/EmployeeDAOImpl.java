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

	// injected via spring config
	@Autowired
	private SessionFactory sessionFactory;
	
	public EmployeeDAOImpl() {
		setCLazz(Employee.class);
	}
	
	@Override
	public List<Employee> searchEmployee(String searchName) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Employee> query = null;
		
		if (searchName != null && searchName.trim().length() > 0) {
			String employeeName = searchName.trim().toLowerCase();
			query = session.createQuery("FROM Employee WHERE LOWER(firstName) LIKE :EmployeeName OR LOWER(lastName) like :EmployeeName ORDER BY lastName", Employee.class);
			query.setParameter("EmployeeName", "%" + employeeName + "%");
		} else {
			query = session.createQuery("FROM Employee ORDER BY lastName", Employee.class);
		}
		List<Employee> Employees = query.getResultList();
		return Employees;
	}
}
