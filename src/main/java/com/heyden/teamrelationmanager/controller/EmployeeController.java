package com.heyden.teamrelationmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@GetMapping("/list")
	public String getEmployeeList(Model model) {
		
		
		return "employee/list";
	}
}
