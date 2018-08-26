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
package com.heyden.teamrelationmanager.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.error.EntityNotFoundException;
import com.heyden.teamrelationmanager.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeesRestController {
	
	@Autowired
	private EmployeeService employeeService; 
	
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		List<Employee> employees = new ArrayList<>();
		employees.addAll(employeeService.getEmployees(Employee.COLUMN_LAST_NAME));
		return employees;
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployees(@PathVariable int id){
		Employee employee = employeeService.getEmployee(id);
		if (employee == null) {
			throw new EntityNotFoundException("There is no employee with id: " + id);
		}
		return employee;
	}

	@PostMapping("/employees")
	public EntitySuccesResponse addEmployee(@RequestBody Employee employee) {
		// force a save of new item, instead of an unwanted update
		employee.setId(0);
		employeeService.saveEmployee(employee);
		
		return new EntitySuccesResponse(employee, "Adding the employee successfully", HttpStatus.OK.value(), System.currentTimeMillis());
	}
	
	@PutMapping("/employees")
	public EntitySuccesResponse updateEmployee(@RequestBody Employee employee) {
		
		employeeService.saveEmployee(employee);
		
		return new EntitySuccesResponse(employee, "Update the employee successfully", HttpStatus.OK.value(), System.currentTimeMillis());
	}
	
	@DeleteMapping("/employees/{id}")
	public EntitySuccesResponse deleteEmployee(@PathVariable int id) {
		Employee employee = employeeService.getEmployee(id);
		if (employee == null) {
			throw new EntityNotFoundException("There is no employee with id: " + id);
		}
		employeeService.deleteEmployee(id);
		return new EntitySuccesResponse(null, "Delete the employee successfully", HttpStatus.OK.value(), System.currentTimeMillis());
	}
}
