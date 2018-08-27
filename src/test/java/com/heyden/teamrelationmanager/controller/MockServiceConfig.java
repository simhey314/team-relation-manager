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

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.heyden.teamrelationmanager.service.EmployeeService;
import com.heyden.teamrelationmanager.service.TeamService;

/**
 * Configuration to get the mocked service beans
 * 
 * @author Simon Heyden <simon@family-heyden.net>
 *
 */
@Profile("test")
@Configuration
public class MockServiceConfig {

	@Bean
	@Primary
	public EmployeeService employeeService() {
		return Mockito.mock(EmployeeService.class);
	}
	
	@Bean
	@Primary
	public TeamService teamService() {
		return Mockito.mock(TeamService.class);
	}	
}
