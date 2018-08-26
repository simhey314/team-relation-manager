package com.heyden.teamrelationmanager.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

	@Override
	public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
		
		if (StringUtils.isBlank(fieldName) || !fieldName.equals("name") ) {
			throw new UnsupportedOperationException("Field name: ["+fieldName+"] not supported");
		}
		
		if (value == null) {
			return false;
		}
		
		return teamDAO.existsByName(value.toString());
	}
}