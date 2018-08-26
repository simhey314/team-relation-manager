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
package com.heyden.teamrelationmanager.service;

import java.util.List;

import com.heyden.teamrelationmanager.entity.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public List<Employee> getEmployees(String orderByColumn);
	
	public void saveEmployee(Employee Employee);

	public Employee getEmployee(int id);

	public void deleteEmployee(int id);

	public List<Employee> searchEmployee(String searchName);
}
