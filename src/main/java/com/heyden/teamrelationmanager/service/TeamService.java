package com.heyden.teamrelationmanager.service;

import java.util.List;

import com.heyden.teamrelationmanager.entity.Team;

public interface TeamService extends FieldUniqueness {

	public List<Team> getTeams();
	
	public List<Team> getTeams(String orderByColumn);

	public void saveTeam(Team Team);

	public Team getTeam(int id);

	public void deleteTeam(int id);
}
