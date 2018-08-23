package com.heyden.teamrelationmanager.advice;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
	public ModelAndView controllerExcpetionHandling(Exception exception) {
		ModelAndView result = new ModelAndView();
		result.setViewName("error/detail");
		return result;
	}
}
