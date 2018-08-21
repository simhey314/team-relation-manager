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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonLoggingAspect {
	
	private static final String RESULT_IS_NULL = "result is null";
	private static final String ARG_IS_NULL = "arg is null";
	private static final Logger LOG = LogManager.getLogger(CommonLoggingAspect.class); 
	
	@Before("com.heyden.teamrelationmanager.aspect.PointcutExpression.controllerDaoService()")
	public void loggingAppFlowBefore(JoinPoint joinPoint) {
		LOG.info("@Before on method: {}", joinPoint.getSignature().toShortString());
		for (Object arg : joinPoint.getArgs()) {

			LOG.debug("@Before argument: Type [{}] Value [{}]", getTypeName(arg, ARG_IS_NULL) , arg);
		} 
	}
	
	@AfterReturning(pointcut="com.heyden.teamrelationmanager.aspect.PointcutExpression.controllerDaoService()",
					returning="result")
	public void loggingAppFlowAfterReturning(JoinPoint joinPoint, Object result) {
		LOG.debug("@AfterReturning on method: {}", joinPoint.getSignature().toShortString());
		LOG.debug("@AfterReturning result: Type [{}] Value [{}]", getTypeName(result, RESULT_IS_NULL));
	}
	
	@AfterThrowing(pointcut="com.heyden.teamrelationmanager.aspect.PointcutExpression.controllerPackage()",
			       throwing="exception")
	public void loggingExceptions(JoinPoint joinPoint, Exception exception) {
		LOG.error("Exception is thrown on method: " + joinPoint.getSignature().toShortString() + "\n", exception);
	}
	
	private String getTypeName(Object object, String defaultValue) {
		String type = defaultValue;
		if (object != null) {
			type = object.getClass().getName();
		}
		return type;
	}
}
