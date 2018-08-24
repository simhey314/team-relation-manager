package com.heyden.teamrelationmanager.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heyden.teamrelationmanager.entity.Team;
import com.heyden.teamrelationmanager.error.EntityNotFoundException;
import com.heyden.teamrelationmanager.service.TeamService;

@RestController
@RequestMapping("/api")
public class TeamRestController {
	
	@Autowired
	private TeamService teamService; 
	
	@GetMapping("/teams")
	public List<Team> getTeams(){
		List<Team> teams = new ArrayList<>();
		teams.addAll(teamService.getTeams(Team.COLUMN_NAME));
		return teams;
	}

	@GetMapping("/teams/{id}")
	public Team getTeams(@PathVariable int id){
		Team team = teamService.getTeam(id);
		if (team == null) {
			throw new EntityNotFoundException("There is no team with id: " + id);
		}
		return team;
	}

	@PostMapping("/teams")
	public EntitySuccesResponse addTeam(@RequestBody Team team) {
		// force a save of new item, instead of an unwanted update
		team.setId(0);
		teamService.saveTeam(team);
		
		return new EntitySuccesResponse(team, "Adding the team successfully", HttpStatus.OK.value(), System.currentTimeMillis());
	}
	
	@PutMapping("/teams")
	public EntitySuccesResponse updateTeam(@RequestBody Team team) {
		
		teamService.saveTeam(team);
		
		return new EntitySuccesResponse(team, "Update the team successfully", HttpStatus.OK.value(), System.currentTimeMillis());
	}
	
	@DeleteMapping("/teams/{id}")
	public EntitySuccesResponse deleteTeam(@PathVariable int id) {
		Team team = teamService.getTeam(id);
		if (team == null) {
			throw new EntityNotFoundException("There is no team with id: " + id);
		}
		teamService.deleteTeam(id);
		return new EntitySuccesResponse(null, "Delete the team successfully", HttpStatus.OK.value(), System.currentTimeMillis());
	}
}
