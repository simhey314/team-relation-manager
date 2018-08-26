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
package com.heyden.teamrelationmanager.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.heyden.teamrelationmanager.rest")
public class RestExceptionHandler {
	
	private static final Logger LOG = LogManager.getLogger(RestExceptionHandler.class);

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> restControllerExcpetionHandler(EntityNotFoundException exception) {
		EntityErrorResponse response = new EntityErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		LOG.error("Restcontroller exception catched:", exception);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> catchAllExcpetionHandler(Exception exception) {
		EntityErrorResponse response = new EntityErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		LOG.error("Restcontroller exception catched:", exception);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
