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

import com.heyden.teamrelationmanager.ApplicationConstants;
import com.heyden.teamrelationmanager.entity.Team;
import com.heyden.teamrelationmanager.service.TeamService;

@Controller
@RequestMapping(TeamController.PATH_TEAM)
public class TeamController {

	static final String KEY_TEAMS = "teams";
	static final String PARAM_ID = "id";
	static final String PARAM_TEAM = "team";
	static final String PATH_CREATE = "/create";
	static final String PATH_LIST = "/list";
	static final String PATH_DETAIL = "/detail";
	static final String PATH_DELETE = "/delete";
	static final String PATH_SAVE = "/save";
	static final String VIEW_TEAM_LIST = "team/list";
	static final String VIEW_TEAM_DETAIL = "team/detail";
	static final String REDIRECT_TEAM_LIST = ApplicationConstants.VIEW_REDIRECT + VIEW_TEAM_LIST;
	static final String PATH_TEAM = "/team";
	static final String KEY_TEAM = "team";
	static final String PARAM_NAME = "name";

	@Autowired
	private TeamService teamService;

	@PostMapping(PATH_SAVE)
	public String saveTeam(@Valid @ModelAttribute(PARAM_TEAM) final Team team, final BindingResult bindingResult, final Model model) {
		String view = REDIRECT_TEAM_LIST;

		if (bindingResult.hasErrors()) {
			model.addAttribute(PARAM_TEAM, team);
			view = VIEW_TEAM_DETAIL;
		} else {
			teamService.saveTeam(team);
		}

		return view;
	}

	@GetMapping(PATH_DELETE)
	public String deleteTeam(@RequestParam(PARAM_ID) final int teamId, final Model model) {
		teamService.deleteTeam(teamId);
		return REDIRECT_TEAM_LIST;
	}

	@GetMapping(PATH_DETAIL)
	public String detailTeam(@RequestParam(PARAM_ID) final int teamId, final Model model) {
		Team team = teamService.getTeam(teamId);
		if (team == null) {
			return ApplicationConstants.VIEW_ERROR_DETAIL;
		}
		model.addAttribute(PARAM_TEAM, team);
		return VIEW_TEAM_DETAIL;
	}

	@GetMapping(PATH_LIST)
	public String listTeam(final Model model) {

		model.addAttribute(KEY_TEAMS, teamService.getTeams(Team.COLUMN_NAME));

		return VIEW_TEAM_LIST;
	}

	@GetMapping(PATH_CREATE)
	public String createTeam(final Model model) {

		model.addAttribute(KEY_TEAM, new Team());

		return VIEW_TEAM_DETAIL;
	}
}
