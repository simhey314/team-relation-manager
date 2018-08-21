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
	
	@GetMapping("/detail")
	public String detailTeam(@RequestParam("teamId") int teamId, Model model) {
		model.addAttribute("team", teamService.getTeam(teamId));
		return "team/detail";
	}
	
	@GetMapping("/list")
	public String listTeam(Model model) {
		
		model.addAttribute("teams", teamService.getTeams(Team.COLUMN_NAME));
		
		return "team/list";
	}
	
	@GetMapping("/create")
	public String createTeam(Model model) {
		
		model.addAttribute("team", new Team());
		
		return "team/detail";
	}
}
