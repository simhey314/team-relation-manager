package com.heyden.teamrelationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.entity.Team;
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
		List<Team> teamList = teamService.getTeams(Team.COLUMN_NAME);
		model.addAttribute("employees", employeeList);
		model.addAttribute("teams", teamList);
		
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

	@PostMapping("/detail")
	public String getSearchResult(@RequestParam("employeeId") int employeeId, Model model) {
		
		model.addAttribute("employee", employeeService.getEmployee(employeeId));

		return "employee/detail";
	}
}
