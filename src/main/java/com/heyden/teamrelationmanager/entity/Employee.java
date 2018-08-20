package com.heyden.teamrelationmanager.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@NotNull(message="string.required")
	@Size(min=1, message="string.required")
	@Column(name="first_name")
	private String firstName;

	@NotNull(message="string.required")
	@Size(min=1, message="string.required")
	@Column(name="last_name")
	private String lasttName;
	
	@Pattern(regexp="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,8}$", message="email.pattern")
	@Column
	private String email;

	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="team_id")
	private Team team;

	public Employee() {}
	
	public Employee(String firstName, String lasttName, String email) {
		super();
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasttName() {
		return lasttName;
	}

	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return String.format("Employee [id=%s, firstName=%s, lasttName=%s, email=%s, team=%s]", id, firstName,
				lasttName, email, team != null ? team.getName() : null);
	}
}
