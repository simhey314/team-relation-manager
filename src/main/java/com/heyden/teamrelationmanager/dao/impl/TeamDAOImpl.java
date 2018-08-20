package com.heyden.teamrelationmanager.dao.impl;

import org.springframework.stereotype.Repository;

import com.heyden.teamrelationmanager.dao.TeamDAO;
import com.heyden.teamrelationmanager.entity.Team;

@Repository
class TeamDAOImpl extends GenericDAOHibernateImpl<Team, Integer> implements TeamDAO {
	
	public TeamDAOImpl() {
		setCLazz(Team.class);
	}
}
