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
package com.heyden.teamrelationmanager.service;

import java.util.List;

import com.heyden.teamrelationmanager.entity.Team;

public interface TeamService {

	public List<Team> getTeams();

	public List<Team> getTeams(String orderByColumn);

	public void saveTeam(Team Team);

	public Team getTeam(int id);

	public void deleteTeam(int id);
}
