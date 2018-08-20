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
	public void saveTeam(Team Team) {
		teamDAO.save(Team);
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
}