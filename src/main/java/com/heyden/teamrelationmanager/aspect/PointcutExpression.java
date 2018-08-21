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

package com.heyden.teamrelationmanager.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutExpression {
	
	@Pointcut("execution(* com.heyden.teamrelationmanager.controller.*.*(..))")
	public void controllerPackage() {}
	
	@Pointcut("execution(* com.heyden.teamrelationmanager.dao.*.*(..))")
	private void daoPackage() {}
	
	@Pointcut("execution(* com.heyden.teamrelationmanager.service.*.*(..))")
	private void servicePackage() {}
	
	@Pointcut("controllerPackage() || daoPackage() || servicePackage()")
	public void controllerDaoService() {}
}
