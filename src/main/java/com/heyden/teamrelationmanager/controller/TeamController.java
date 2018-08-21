package com.heyden.teamrelationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heyden.teamrelationmanager.entity.Team;
import com.heyden.teamrelationmanager.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;

	@PostMapping("/save")
	public String saveTeam(Model model) {
		return "redirect:/team/list";
	}
	
	@PostMapping("/delete")
	public String deleteTeam(@RequestParam("teamId") int teamId, Model model) {
		teamService.deleteTeam(teamId);
		return "redirect:/team/list";
	}
	
	@PostMapping("/detail")
	public String detailTeam(@RequestParam("teamId") int teamId, Model model) {
		model.addAttribute("team", teamService.getTeam(teamId));
		return "redirect:/team/list";
	}
	
	@GetMapping("/list")
	public String listTeam(Model model) {
		
		model.addAttribute("teams", teamService.getTeams(Team.COLUMN_NAME));
		
		return "team/list";
	}
}
