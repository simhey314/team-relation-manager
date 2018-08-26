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

package com.heyden.teamrelationmanager.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.heyden.teamrelationmanager.dao.TeamDAO;
import com.heyden.teamrelationmanager.entity.Employee;
import com.heyden.teamrelationmanager.entity.Team;

@Repository
class TeamDAOImpl extends GenericDAOHibernateImpl<Team, Integer> implements TeamDAO {

	public TeamDAOImpl() {
		setClazz(Team.class);
	}

	@Override
	public void delete(Team dataObject) {

		for (Employee employee : dataObject.getEmployees()) {
			employee.setTeam(null);
			getSession().update(employee);
		}

		super.delete(dataObject);
	}

	@Override
	public boolean existsByName(String name) {
		boolean result = false;
		if (StringUtils.isNotBlank(name)) {
			Query<Integer> query = getSession().createQuery("SELECT count(*) FROM Team WHERE name = :Name", Integer.class);
			query.setParameter("Name", name);
			Integer countName = query.getSingleResult();
			result = countName != null && countName.intValue() == 1;
		}
		return result;
	}
}
