/**
 * Copyright 2018 Simon Heyden
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
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
@RequestMapping(EmployeeController.PATH_EMPLOYEE)
public class EmployeeController {

	static final String MESSAGE_NO_SEARCH_RESULT = "Bei der Suche wurden keine Mitarbeiter/in gefunden!";
	static final String KEY_MESSAGE = "message";
	static final String PARAM_ID = "id";
	static final String PATH_EMPLOYEE = "/employee";
	static final String PATH_LIST = "/list";
	static final String PATH_SEARCH = "/search";
	static final String PATH_CREATE = "/create";
	static final String PATH_DETAIL = "/detail";
	static final String PATH_SAVE = "/save";
	static final String PATH_DELETE = "/delete";
	static final String KEY_SEARCH_VALUE = "searchValue";
	static final String KEY_TEAMS = "teams";
	static final String KEY_EMPLOYEES = "employees";
	static final String KEY_EMPLOYEE = "employee";
	static final String PARAM_SEARCH_VALUE = KEY_SEARCH_VALUE;
	static final String VIEW_EMPLOYEE_LIST = "employee/list";
	static final String VIEW_EMPLOYEE_DETAIL = "employee/detail";
	static final String REDIRECT_EMPLOYEE_LIST = "redirect:/" + VIEW_EMPLOYEE_LIST;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private TeamService teamService;

	@GetMapping(PATH_LIST)
	public String getEmployeeList(final Model model) {

		List<Employee> employeeList = employeeService.getEmployees(Employee.COLUMN_LAST_NAME);
		model.addAttribute(KEY_EMPLOYEES, employeeList);

		return VIEW_EMPLOYEE_LIST;
	}

	@PostMapping(PATH_SEARCH)
	public String getSearchResult(@RequestParam(PARAM_SEARCH_VALUE) final String searchValue, final Model model) {

		List<Employee> employeeList = employeeService.searchEmployee(searchValue);
		model.addAttribute(KEY_EMPLOYEES, employeeList);
		// TODO: externalize the message
		model.addAttribute(KEY_MESSAGE, MESSAGE_NO_SEARCH_RESULT);
		model.addAttribute(KEY_SEARCH_VALUE, searchValue);

		return VIEW_EMPLOYEE_LIST;
	}

	@GetMapping(PATH_CREATE)
	public String createEmployee(final Model model) {
		Employee newEmloyee = new Employee();
		model.addAttribute(KEY_EMPLOYEE, newEmloyee);
		model.addAttribute(KEY_TEAMS, teamService.getTeams());

		return VIEW_EMPLOYEE_DETAIL;
	}

	@GetMapping(PATH_DETAIL)
	public String getDetailEmployee(@RequestParam(PARAM_ID) final int employeeId, final Model model) {
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			return REDIRECT_EMPLOYEE_LIST;
		}
		model.addAttribute(KEY_EMPLOYEE, employee);
		model.addAttribute(KEY_TEAMS, teamService.getTeams());

		return VIEW_EMPLOYEE_DETAIL;
	}

	@PostMapping(PATH_SAVE)
	public String saveEmployee(@Valid @ModelAttribute(KEY_EMPLOYEE) final Employee employee,
		final BindingResult bindingResult, final Model model) {

		String view = REDIRECT_EMPLOYEE_LIST;
		if (bindingResult.hasErrors()) {
			view = VIEW_EMPLOYEE_DETAIL;
			model.addAttribute(KEY_EMPLOYEE, employee);
			model.addAttribute(KEY_TEAMS, teamService.getTeams());
		} else {
			employeeService.saveEmployee(employee);
		}

		return view;
	}

	@GetMapping(PATH_DELETE)
	public String deleteEmployee(@RequestParam(PARAM_ID) final int id, final Model model) {
		employeeService.deleteEmployee(id);
		return REDIRECT_EMPLOYEE_LIST;
	}
}
