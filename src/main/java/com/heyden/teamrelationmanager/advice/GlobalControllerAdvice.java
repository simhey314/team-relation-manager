package com.heyden.teamrelationmanager.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	
	
	private static final String VIEW_ERROR_DETAIL = "error/detail";
	private static final Logger LOG = LogManager.getLogger(GlobalControllerAdvice.class);

	@ExceptionHandler({MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
	public ModelAndView controllerExcpetionHandling(Exception exception) {
		ModelAndView result = new ModelAndView();
		result.setViewName(VIEW_ERROR_DETAIL);
		LOG.error("Controller exception catched, show view: " + VIEW_ERROR_DETAIL, exception);
		return result;
	}
}
