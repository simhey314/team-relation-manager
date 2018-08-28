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
package com.heyden.teamrelationmanager.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.heyden.teamrelationmanager.ApplicationConstants;

@ControllerAdvice("com.heyden.teamrelationmanager.controller")
public class ControllerExceptionHandler {

	private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class })
	public ModelAndView controllerExcpetionHandling(final Exception exception) {
		ModelAndView result = new ModelAndView();
		result.setStatus(HttpStatus.BAD_REQUEST);
		result.setViewName(ApplicationConstants.VIEW_ERROR_DETAIL);
		LOG.error("Controller exception catched, show view: " + ApplicationConstants.VIEW_ERROR_DETAIL, exception);
		return result;
	}
}
