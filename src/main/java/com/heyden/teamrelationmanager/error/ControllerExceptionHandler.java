package com.heyden.teamrelationmanager.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.heyden.teamrelationmanager.ApplicationConstants;

@ControllerAdvice("com.heyden.teamrelationmanager.controller")
public class ControllerExceptionHandler {
	
	private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler({MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
	public ModelAndView controllerExcpetionHandler(Exception exception) {
		ModelAndView result = new ModelAndView();
		result.setViewName(ApplicationConstants.VIEW_ERROR_DETAIL);
		LOG.error("Controller exception catched, show view: " + ApplicationConstants.VIEW_ERROR_DETAIL, exception);
		return result;
	}
}
