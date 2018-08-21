/** Copyright 2018 Simon Heyden

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
**/

package com.heyden.teamrelationmanager.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="employee")
public class Employee {

	public static final String COLUMN_LAST_NAME = "lastName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@NotBlank()
	@Column(name="first_name")
	private String firstName;

	@NotBlank()
	@Column(name="last_name")
	private String lastName;
	
	@Pattern(regexp="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,8}$")
	@Column
	private String email;

	@ManyToOne(fetch=FetchType.EAGER,
			   cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="team_id")
	private Team team;

	public Employee() {}
	
	public Employee(String firstName, String lasttName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lasttName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
				lastName, email, team != null ? team.getName() : null);
	}
}
