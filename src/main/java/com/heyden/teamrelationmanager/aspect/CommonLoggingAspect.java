package com.heyden.teamrelationmanager.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonLoggingAspect {
	
	private static final Logger LOG = LogManager.getLogger(CommonLoggingAspect.class); 
	
	@Pointcut("execution(* com.heyden.teamrelationmanager.controller.*.*(..))")
	private void controllerPackage() {}
	
	@Pointcut("execution(* com.heyden.teamrelationmanager.dao.*.*(..))")
	private void daoPackage() {}
	
	@Pointcut("execution(* com.heyden.teamrelationmanager.*.*(..))")
	private void servicePackage() {}
	
	@Pointcut("controllerPackage() || daoPackage() || servicePackage()")
	private void appFlowPackages() {}
	
	
	@Before("appFlowPackages()")
	public void loggingAppFlowBefore(JoinPoint joinPoint) {
		System.out.println("Logging Aspect in Action");
		LOG.info("======>> Logging app flow aspect @Before on method: " + joinPoint.getSignature().toShortString());
		for (Object arg : joinPoint.getArgs()) {
			LOG.debug("=========>> Argument: " + arg.getClass().getName() + ": " + arg);
		} 
	}
	
	@AfterReturning(pointcut="appFlowPackages()",
					returning="result")
	public void loggingAppFlowAfterReturning(JoinPoint joinPoint, Object result) {
		LOG.debug("======>> Logging app flow aspect @AfterReturning on method: " + joinPoint.getSignature().toShortString());
		LOG.debug("=========>> Result: " + result.getClass().getName() + ": " + result);
	}
}
