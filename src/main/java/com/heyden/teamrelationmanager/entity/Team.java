package com.heyden.teamrelationmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="team")
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column()
	private String name;
	
	@OneToMany(mappedBy="employee", cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Employee> employees;
	
	public Team() {}

	public Team(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee newTeammember) {
		if (newTeammember == null) {
			return;
		}
		
		if (employees == null) {
			employees = new ArrayList<>();
		}
		
		employees.add(newTeammember);
		newTeammember.setTeam(this);
	}

	@Override
	public String toString() {
		return String.format("Team [id=%s, name=%s, employees=%s]", id, name, employees);
	}
}
