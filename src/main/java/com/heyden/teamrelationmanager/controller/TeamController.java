package com.heyden.teamrelationmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@GetMapping("/save")
	public String getEmployeeList(Model model) {
		return "team/save";
	}
}
