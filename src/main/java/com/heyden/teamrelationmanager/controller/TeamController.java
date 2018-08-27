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

import com.heyden.teamrelationmanager.entity.Team;
import com.heyden.teamrelationmanager.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {

	private static final String VIEW_TEAM_LIST = "team/list";
	private static final String VIEW_TEAM_DETAIL = "team/detail";
	private static final String REDIRECT_TEAM_LIST = "redirect:/" + VIEW_TEAM_LIST;

	@Autowired
	private TeamService teamService;

	@PostMapping("/save")
	public String saveTeam(@Valid @ModelAttribute("team") final Team team, final BindingResult bindingResult, final Model model) {
		String view = REDIRECT_TEAM_LIST;

		if (bindingResult.hasErrors()) {
			model.addAttribute("team", team);
			view = VIEW_TEAM_DETAIL;
		} else {
			teamService.saveTeam(team);
		}

		return view;
	}

	@GetMapping("/delete")
	public String deleteTeam(@RequestParam("id") final int teamId, final Model model) {
		teamService.deleteTeam(teamId);
		return REDIRECT_TEAM_LIST;
	}

	@GetMapping("/detail")
	public String detailTeam(@RequestParam("id") final int teamId, final Model model) {
		Team team = teamService.getTeam(teamId);
		if (team == null) {
			return REDIRECT_TEAM_LIST;
		}
		model.addAttribute("team", team);
		return VIEW_TEAM_DETAIL;
	}

	@GetMapping("/list")
	public String listTeam(final Model model) {

		model.addAttribute("teams", teamService.getTeams(Team.COLUMN_NAME));

		return VIEW_TEAM_LIST;
	}

	@GetMapping("/create")
	public String createTeam(final Model model) {

		model.addAttribute("team", new Team());

		return VIEW_TEAM_DETAIL;
	}
}
