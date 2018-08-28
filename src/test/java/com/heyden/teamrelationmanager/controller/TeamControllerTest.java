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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.heyden.teamrelationmanager.ApplicationConstants;

import com.heyden.teamrelationmanager.entity.Team;
import com.heyden.teamrelationmanager.error.ControllerExceptionHandler;
import com.heyden.teamrelationmanager.error.ControllerExceptionResolver;
import com.heyden.teamrelationmanager.mockito.MockitoExtension;
import com.heyden.teamrelationmanager.service.TeamService;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 */
@ExtendWith(MockitoExtension.class)
class TeamControllerTest {
	
	private static final int TEAM_ID = 2718;
	private static final String TEAM_NAME = "teamname";

	private MockMvc mockMvc;
	
	@Mock
	private TeamService teamService;

	@InjectMocks
	private TeamController teamController;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(teamController)
                .setControllerAdvice(new ControllerBindingHandler())
                .setControllerAdvice(new ControllerExceptionHandler())
                .setHandlerExceptionResolvers(new ControllerExceptionResolver())
                .build();
	}
	
	@ParameterizedTest
	@MethodSource("pathGetProvider")
	void testUnhandledPostRequest(String path) throws Exception {
		ModelAndView actual = mockMvc.perform(post(path))
			.andReturn().getModelAndView();
		
		assertThat(actual).isNotNull();
		assertThat(actual.getStatus()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
		assertThat(actual.getViewName()).isEqualTo(ApplicationConstants.VIEW_ERROR_DETAIL);
	}
	
	@ParameterizedTest
	@MethodSource("pathPostProvider")
	void testUnhandledGetRequest(String path) throws Exception {
		ModelAndView actual = mockMvc.perform(get(path))
			.andReturn().getModelAndView();
		
		assertThat(actual).isNotNull();
		assertThat(actual.getStatus()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
		assertThat(actual.getViewName()).isEqualTo(ApplicationConstants.VIEW_ERROR_DETAIL);
	}

	@Test
	void testGetTeamList() throws Exception {
		List<Team> expectedTeamList = getTeamList();
		when(teamService.getTeams(Team.COLUMN_NAME)).thenReturn(expectedTeamList);
		
		ModelAndView actual = mockMvc.perform(get(getPath(TeamController.PATH_LIST)))
		.andDo(MockMvcResultHandlers.log())
		.andReturn().getModelAndView();
		
		verify(teamService).getTeams(Team.COLUMN_NAME);
		assertThat(actual.getViewName()).isEqualTo(TeamController.VIEW_TEAM_LIST);
		assertThat(actual.getModelMap().get(TeamController.KEY_TEAMS)).isEqualTo(expectedTeamList);
	}
	
	@Test
	void testCreateTeam() throws Exception {
		Team expectedTeam = new Team();
		
		ModelAndView actual = mockMvc.perform(get(getPath(TeamController.PATH_CREATE)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		assertThat(actual.getViewName()).isEqualTo(TeamController.VIEW_TEAM_DETAIL);
		assertThat(actual.getModelMap().get(TeamController.KEY_TEAM)).isEqualToComparingFieldByField(expectedTeam);
	}


	@Test
	void testDetailTeam() throws Exception {
		Team expectedTeam = getTeam();
		when(teamService.getTeam(TEAM_ID)).thenReturn(expectedTeam);
		
		ModelAndView actual = mockMvc
			.perform(
				get(getPath(TeamController.PATH_DETAIL))
				.param(TeamController.PARAM_ID, Integer.toString(TEAM_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(teamService).getTeam(TEAM_ID);
		assertThat(actual.getViewName()).isEqualTo(TeamController.VIEW_TEAM_DETAIL);
		assertThat(actual.getModelMap().get(TeamController.KEY_TEAM)).isEqualTo(expectedTeam);
	}

	@Test
	void testDetailTeamNotFound() throws Exception {
		when(teamService.getTeam(TEAM_ID)).thenReturn(null);
		
		ModelAndView actual = mockMvc
			.perform(
				get(getPath(TeamController.PATH_DETAIL))
				.param(TeamController.PARAM_ID, Integer.toString(TEAM_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(teamService).getTeam(TEAM_ID);
		assertThat(actual.getViewName()).isEqualTo(ApplicationConstants.VIEW_ERROR_DETAIL);
	}
	
	@Test
	void testSaveTeam() throws Exception {
		ModelAndView actual = mockMvc
			.perform(
				post(getPath(TeamController.PATH_SAVE))
				.param(TeamController.PARAM_NAME, TEAM_NAME))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(teamService).saveTeam(ArgumentMatchers.any(Team.class));
		assertThat(actual.getViewName()).isEqualTo(TeamController.REDIRECT_TEAM_LIST);
	}
	
	@Test
	void testSaveTeamInvalid() throws Exception {
		Team  expectedTeam = new Team();
		expectedTeam.setId(TEAM_ID);

		ModelAndView actual = mockMvc
			.perform(
				post(getPath(TeamController.PATH_SAVE))
				.param(TeamController.PARAM_ID, Integer.toString(TEAM_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(teamService, times(0)).saveTeam(ArgumentMatchers.any(Team.class));
		assertThat(actual.getViewName()).isEqualTo(TeamController.VIEW_TEAM_DETAIL);
		assertThat(actual.getModelMap().get(TeamController.KEY_TEAM)).isEqualToComparingFieldByField(expectedTeam);
	}

	@Test
	void testDeleteTeam() throws Exception {
		ModelAndView actual = mockMvc
			.perform(
				get(getPath(TeamController.PATH_DELETE))
				.param(TeamController.PARAM_ID, Integer.toString(TEAM_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();

		verify(teamService).deleteTeam(TEAM_ID);
		assertThat(actual.getViewName()).isEqualTo(TeamController.REDIRECT_TEAM_LIST);
	}
	
	static Stream<String> pathGetProvider(){
		return Stream.of(getPath(TeamController.PATH_LIST),
			getPath(TeamController.PATH_CREATE),
			getPath(TeamController.PATH_DELETE),
			getPath(TeamController.PATH_DETAIL));
	}
	
	static Stream<String> pathPostProvider(){
		return Stream.of(getPath(TeamController.PATH_SAVE));
	}
	
	static String getPath(String pathSuffix) {
		return String.format("%s%s", TeamController.PATH_TEAM, pathSuffix);
	}
	
	static List<Team> getTeamList() {
		List<Team> expectedTeamList = new ArrayList<>();
		expectedTeamList.add(getTeam());
		return expectedTeamList;
	}

	static Team getTeam() {
		return new Team(TEAM_NAME);
	}
}
