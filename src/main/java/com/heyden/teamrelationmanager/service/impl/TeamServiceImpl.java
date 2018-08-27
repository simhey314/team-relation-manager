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
package com.heyden.teamrelationmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heyden.teamrelationmanager.dao.TeamDAO;
import com.heyden.teamrelationmanager.entity.Team;
import com.heyden.teamrelationmanager.service.TeamService;

@Service
class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	@Transactional
	public List<Team> getTeams() {
		return teamDAO.getAll();
	}

	@Override
	@Transactional
	public void saveTeam(Team team) {
		teamDAO.save(team);
	}

	@Override
	@Transactional
	public Team getTeam(int id) {
		return teamDAO.get(id);
	}

	@Override
	@Transactional
	public void deleteTeam(int id) {
		teamDAO.deleteById(id);		
	}

	@Override
	@Transactional
	public List<Team> getTeams(String orderByColumn) {
		return teamDAO.getAllOrderBy(orderByColumn);
	}
}