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
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.heyden.teamrelationmanager.ApplicationConstants;
import com.heyden.teamrelationmanager.config.WebAppConfig;
import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.service.EmployeeService;
import com.heyden.teamrelationmanager.service.TeamService;

/**
 * Testing the mvc employee controller
 * 
 * @author Simon Heyden <simon@family-heyden.net>
 *
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {WebAppConfig.class, MockServiceConfig.class})
@WebAppConfiguration
class EmployeeControllerTest {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TeamService teamService;
	
	
	@Autowired
	private WebApplicationContext webContext;
	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	void testContextConfig() {
		ServletContext underTest = webContext.getServletContext();
		
		assertThat(underTest).isNotNull();
		assertThat(underTest).isInstanceOf(MockServletContext.class);
		assertThat(webContext.getBean("employeeController")).isNotNull();
	}

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.controller.EmployeeController#getEmployeeList(org.springframework.ui.Model)} get request.
	 * @throws Exception if something going wrong
	 */
	@Test
	void testGetEmployeeListGetRequest() throws Exception {
		List<Employee> expectedEmployeeList = new ArrayList<>();	
		Mockito.when(employeeService.getEmployees()).thenReturn(expectedEmployeeList);

		ModelAndView actual = mockMvc.perform(get(getPath(EmployeeController.PATH_LIST)))
			.andDo(MockMvcResultHandlers.log())
			.andReturn().getModelAndView();
		
		assertThat(actual).isNotNull();
		assertThat(actual.getStatus()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(actual.getViewName()).isEqualTo(EmployeeController.VIEW_EMPLOYEE_LIST);
		assertThat(actual.getModel()).isNotNull();
		assertThat(actual.getModel().get(EmployeeController.KEY_EMPLOYEES)).isEqualTo(expectedEmployeeList);
	}
	
	/**
	 * Test method for {@link com.heyden.teamrelationmanager.controller.EmployeeController#getEmployeeList(org.springframework.ui.Model)} post request.
	 * @throws Exception if something going wrong
	 */
	@Test
	void testGetEmployeeListPostRequest() throws Exception {
		ModelAndView actual = mockMvc.perform(post(getPath(EmployeeController.PATH_LIST)))
			.andReturn().getModelAndView();
		
		assertThat(actual).isNotNull();
		assertThat(actual.getStatus()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
		assertThat(actual.getViewName()).isEqualTo(ApplicationConstants.VIEW_ERROR_DETAIL);
	}

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.controller.EmployeeController#getSearchResult(java.lang.String, org.springframework.ui.Model)}.
	 * @throws Exception something going wrong
	 */
	@Test
	void testGetSearchResultGetRequest() throws Exception {
		ModelAndView actual = mockMvc.perform(post(getPath(EmployeeController.PATH_SEARCH)))
			.andReturn().getModelAndView();
		
		assertThat(actual).isNotNull();
		assertThat(actual.getStatus()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
		assertThat(actual.getViewName()).isEqualTo(ApplicationConstants.VIEW_ERROR_DETAIL);
	}
	
	@Test
	@Paramet
	
	void testUnhandledPostRequest() throws Exception {
		
	}

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.controller.EmployeeController#createEmployee(org.springframework.ui.Model)}.
	 */
	@Test
	void testCreateEmployee() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.controller.EmployeeController#getDetailEmployee(int, org.springframework.ui.Model)}.
	 */
	@Test
	void testGetDetailEmployee() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.controller.EmployeeController#saveEmployee(com.heyden.teamrelationmanager.entity.Employee, org.springframework.validation.BindingResult, org.springframework.ui.Model)}.
	 */
	@Test
	void testSaveEmployee() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.controller.EmployeeController#deleteEmployee(int, org.springframework.ui.Model)}.
	 */
	@Test
	void testDeleteEmployee() {
		fail("Not yet implemented");
	}
	
	/**
	 * @param pathGoal
	 * @return the complete controller request path
	 */
	private String getPath(String pathGoal) {
		return String.format("/%s/%s", EmployeeController.PATH_EMPLOYEE, pathGoal);
	}

}
