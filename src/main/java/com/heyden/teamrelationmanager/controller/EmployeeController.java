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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.service.EmployeeService;
import com.heyden.teamrelationmanager.service.TeamService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TeamService teamService;
	
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
		// TODO: externalize the message 
		model.addAttribute("message", "Bei der Suche wurden keine Mitarbeiter/in gefunden!");
		model.addAttribute("searchValue", searchValue);

		return "employee/list";
	}
	
	@GetMapping("/create")
	public String createEmployee(Model model) {
		Employee newEmloyee = new Employee();
		model.addAttribute("employee", newEmloyee);
		model.addAttribute("teams", teamService.getTeams());
		
		return "employee/detail";
	}

	@GetMapping("/detail")
	public String getDetailEmployee(@RequestParam("employeeId") int employeeId, Model model) {
		
		model.addAttribute("employee", employeeService.getEmployee(employeeId));
		model.addAttribute("teams", teamService.getTeams());

		return "employee/detail";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
		
		String view = "redirect:/employee/list";
		if (bindingResult.hasErrors()) {
			view = "employee/detail";
			model.addAttribute("employee", employee);
			model.addAttribute("teams", teamService.getTeams());
		} else {
			employeeService.saveEmployee(employee);
		}
		
		return view;
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id, Model model) {
		employeeService.deleteEmployee(id);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/leave-team")
	public String leaveTeame(@RequestParam("id") int id, Model model) {
		Employee employee = employeeService.getEmployee(id);
		if (employee != null && employee.getTeam() != null) {
			employee.setTeam(null);
			employeeService.saveEmployee(employee);
		}
		return "redirect:/employee/list";
	}
}
