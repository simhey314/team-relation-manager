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
import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.entity.Team;
import com.heyden.teamrelationmanager.error.ControllerExceptionHandler;
import com.heyden.teamrelationmanager.error.ControllerExceptionResolver;
import com.heyden.teamrelationmanager.mockito.MockitoExtension;
import com.heyden.teamrelationmanager.service.EmployeeService;
import com.heyden.teamrelationmanager.service.TeamService;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 */
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	
	private static final int EMPLOYEE_ID = 3141;
	private static final String LASTNAME = "lastname";
	private static final String FIRSTNAME = "firstname";
	private static final String EMAIL = "email@mail.xx";
	private static final int TEAM_ID = 2718;
	private static final String TEAM_NAME = "teamname";

	private MockMvc mockMvc;
	
	@Mock
	private TeamService teamService;
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
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
	void testGetEmployeeList() throws Exception {
		List<Employee> expectedEmployeeList = getEmployeeList();
		when(employeeService.getEmployees(Employee.COLUMN_LAST_NAME)).thenReturn(expectedEmployeeList);
		
		ModelAndView actual = mockMvc.perform(get(getPath(EmployeeController.PATH_LIST)))
		.andDo(MockMvcResultHandlers.log())
		.andReturn().getModelAndView();
		
		verify(employeeService).getEmployees(Employee.COLUMN_LAST_NAME);
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.VIEW_EMPLOYEE_LIST);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_EMPLOYEES)).isEqualTo(expectedEmployeeList);
	}
	
	@Test
	void testSearchEmployee() throws Exception {
		List<Employee> expectedEmployeeList = getEmployeeList();
		when(employeeService.searchEmployee(FIRSTNAME)).thenReturn(expectedEmployeeList);
		
		ModelAndView actual = mockMvc.perform(post(getPath(EmployeeController.PATH_SEARCH))
			.param(EmployeeController.PARAM_SEARCH_VALUE, FIRSTNAME))
		.andDo(MockMvcResultHandlers.log())
		.andReturn().getModelAndView();
		
		verify(employeeService).searchEmployee(FIRSTNAME);
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.VIEW_EMPLOYEE_LIST);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_EMPLOYEES)).isEqualTo(expectedEmployeeList);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_MESSAGE)).isEqualTo(EmployeeController.MESSAGE_NO_SEARCH_RESULT);
	}

	@Test
	void testCreateEmployee() throws Exception {
		List<Team> expectedTeams = getTeamList();
		when(teamService.getTeams()).thenReturn(expectedTeams);
		Employee expectedEmployee = new Employee();
		
		ModelAndView actual = mockMvc.perform(get(getPath(EmployeeController.PATH_CREATE)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.VIEW_EMPLOYEE_DETAIL);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_TEAMS)).isEqualTo(expectedTeams);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_EMPLOYEE)).isEqualToComparingFieldByField(expectedEmployee);
	}


	@Test
	void testDetailEmployee() throws Exception {
		List<Team> expectedTeams = getTeamList();
		when(teamService.getTeams()).thenReturn(expectedTeams);
		Employee expectedEmployee = getEmployee();
		when(employeeService.getEmployee(EMPLOYEE_ID)).thenReturn(expectedEmployee);
		
		ModelAndView actual = mockMvc
			.perform(
				get(getPath(EmployeeController.PATH_DETAIL))
				.param(EmployeeController.PARAM_ID, Integer.toString(EMPLOYEE_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(employeeService).getEmployee(EMPLOYEE_ID);
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.VIEW_EMPLOYEE_DETAIL);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_TEAMS)).isEqualTo(expectedTeams);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_EMPLOYEE)).isEqualTo(expectedEmployee);
	}

	@Test
	void testDetailEmployeeNotFound() throws Exception {
		when(employeeService.getEmployee(EMPLOYEE_ID)).thenReturn(null);
		
		ModelAndView actual = mockMvc
			.perform(
				get(getPath(EmployeeController.PATH_DETAIL))
				.param(EmployeeController.PARAM_ID, Integer.toString(EMPLOYEE_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(employeeService).getEmployee(EMPLOYEE_ID);
		assertThat(actual.getViewName()).isEqualTo(ApplicationConstants.VIEW_ERROR_DETAIL);
	}
	
	@Test
	void testSaveEmployee() throws Exception {
		ModelAndView actual = mockMvc
			.perform(
				post(getPath(EmployeeController.PATH_SAVE))
				.param(EmployeeController.PARAM_ID, Integer.toString(EMPLOYEE_ID))
				.param(EmployeeController.PARAM_LASTNAME, LASTNAME)
				.param(EmployeeController.PARAM_FIRSTNAME, FIRSTNAME)
				.param(EmployeeController.PARAM_EMAIL, EMAIL)
				.param(EmployeeController.PARAM_TEAM_ID, Integer.toString(TEAM_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(employeeService).saveEmployee(ArgumentMatchers.any(Employee.class));
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.REDIRECT_EMPLOYEE_LIST);
	}
	
	@Test
	void testSaveEmployeeInvalid() throws Exception {
		Employee  expectedEmployee = new Employee();
		expectedEmployee.setId(EMPLOYEE_ID);
		List<Team> expectedTeamList = getTeamList();
		when(teamService.getTeams()).thenReturn(expectedTeamList);

		ModelAndView actual = mockMvc
			.perform(
				post(getPath(EmployeeController.PATH_SAVE))
				.param(EmployeeController.PARAM_ID, Integer.toString(EMPLOYEE_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		verify(employeeService, times(0)).saveEmployee(ArgumentMatchers.any(Employee.class));
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.VIEW_EMPLOYEE_DETAIL);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_TEAMS)).isEqualTo(expectedTeamList);
		assertThat(actual.getModelMap().get(EmployeeController.KEY_EMPLOYEE)).isEqualToComparingFieldByField(expectedEmployee);
	}

	@Test
	void testDeleteEmployee() throws Exception {
		ModelAndView actual = mockMvc
			.perform(
				get(getPath(EmployeeController.PATH_DELETE))
				.param(EmployeeController.PARAM_ID, Integer.toString(EMPLOYEE_ID)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();

		verify(employeeService).deleteEmployee(EMPLOYEE_ID);
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.REDIRECT_EMPLOYEE_LIST);
	}
	
	static Stream<String> pathGetProvider(){
		return Stream.of(getPath(EmployeeController.PATH_LIST),
			getPath(EmployeeController.PATH_CREATE),
			getPath(EmployeeController.PATH_DELETE),
			getPath(EmployeeController.PATH_DETAIL));
	}
	
	static Stream<String> pathPostProvider(){
		return Stream.of(getPath(EmployeeController.PATH_SEARCH),
			getPath(EmployeeController.PATH_SAVE));
	}
	
	static String getPath(String pathSuffix) {
		return String.format("%s%s", EmployeeController.PATH_EMPLOYEE, pathSuffix);
	}
	
	static List<Employee> getEmployeeList() {
		List<Employee> expectedEmployeeList = new ArrayList<>();
		expectedEmployeeList.add(getEmployee());
		return expectedEmployeeList;
	}

	static Employee getEmployee() {
		return new Employee(FIRSTNAME, LASTNAME, EMAIL);
	}
	
	static List<Team> getTeamList() {
		List<Team> expectedTeams = new ArrayList<>();
		expectedTeams.add(new Team(TEAM_NAME));
		return expectedTeams;
	}
}
