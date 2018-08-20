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
	
	@Pointcut("execution(* com.heyden.teamrelationmanager.service.*.*(..))")
	private void servicePackage() {}
	
	@Pointcut("controllerPackage() || daoPackage() || servicePackage()")
	private void appFlowPackages() {}
	
	
	@Before("appFlowPackages()")
	public void loggingAppFlowBefore(JoinPoint joinPoint) {
		LOG.info("@Before on method: {}", joinPoint.getSignature().toShortString());
		for (Object arg : joinPoint.getArgs()) {
			LOG.debug("@Before argument: Type [{}] Value [{}]", arg.getClass().getName(), arg);
		} 
	}
	
	@AfterReturning(pointcut="appFlowPackages()",
					returning="result")
	public void loggingAppFlowAfterReturning(JoinPoint joinPoint, Object result) {
		LOG.debug("@AfterReturning on method: {}", joinPoint.getSignature().toShortString());
		LOG.debug("@AfterReturning result: Type [{}] Value [{}]", result.getClass().getName(), result);
	}
}
