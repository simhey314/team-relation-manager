package com.heyden.teamrelationmanager.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ControllerExceptionReswolver implements HandlerExceptionResolver, Ordered {

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		if (exception instanceof HttpRequestMethodNotSupportedException) {
			ModelAndView view = new ModelAndView();
			view.setViewName("error/detail");
			return view;
		}
		return null;
	}
}
