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
package com.heyden.teamrelationmanager.aspect;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServicePerformanceLogging {

	private static final Logger LOG = LogManager.getLogger(ServicePerformanceLogging.class);

	@Around("com.heyden.teamrelationmanager.aspect.PointcutExpression.servicePackage()")
	public Object aroundController(final ProceedingJoinPoint proceeding) throws Throwable {
		Object result = null;
		StopWatch duration = StopWatch.createStarted();
		try {
			result = proceeding.proceed();
		} finally {
			duration.stop();
			LOG.debug("Data processing on [" + proceeding.getSignature().getName() + "] takes a duration of: " + duration.getTime() + "ms");
		}
		return result;
	}
}
