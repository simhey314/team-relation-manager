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
