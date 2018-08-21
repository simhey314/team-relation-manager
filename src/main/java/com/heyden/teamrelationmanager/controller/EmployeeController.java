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

package com.heyden.teamrelationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public String getEmployeeList(Model model) {
		
		List<Employee> employeeList = employeeService.getEmployees(Employee.COLUMN_LAST_NAME);
		model.addAttribute("employees", employeeList);
		
		return "employee/list";
	}
	
	@PostMapping("/search")
	public String getSearchResult(@RequestParam("searchValue") String searchValue, Model model) {
		
		List<Employee> employeeList = employeeService.searchEmployee(searchValue);
		model.addAttribute("employees", employeeList);

		return "employee/list";
	}
	
	@GetMapping("/create")
	public String createEmployee(Model model) {
		
		model.addAttribute("employee", new Employee());
		
		return "employee/detail";
	}

	@GetMapping("/detail")
	public String getSearchResult(@RequestParam("employeeId") int employeeId, Model model) {
		
		model.addAttribute("employee", employeeService.getEmployee(employeeId));

		return "employee/detail";
	}
	
	@PostMapping("/save")
	public String saveTeam(Model model) {
		
		
		
		return "redirect:/team/list";
	}
	
	@PostMapping("/delete")
	public String deleteTeam(@RequestParam("id") int id, Model model) {
		employeeService.deleteEmployee(id);
		return "redirect:/team/list";
	}
}
