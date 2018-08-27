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


package com.heyden.teamrelationmanager.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Just a test for a junit test class
 * 
 * @author Simon Heyden <simon@family-heyden.net>
 *
 */
class TeamTest {

	private static final String TEAM_NAME = "team_name";

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.entity.Team#Team(java.lang.String)}.
	 */
	@Test
	void testTeamString() {
		Team underTest = new Team(TEAM_NAME);
		
		assertThat(underTest.getName()).isEqualTo(TEAM_NAME);
		assertThat(underTest.getId()).isZero();
		assertThat(underTest.getEmployees()).isNull();
	}

	/**
	 * Test method for {@link com.heyden.teamrelationmanager.entity.Team#setName(java.lang.String)}.
	 */
	@Test
	void testSetName() {
		Team underTest = new Team();
		
		assertThat(underTest.getName()).isNull();
		
		underTest.setName(TEAM_NAME);
		assertThat(underTest.getName()).isEqualTo(TEAM_NAME);
	}
}
