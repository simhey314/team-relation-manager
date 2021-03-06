/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.heyden.teamrelationmanager.mockito;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Parameter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * {@code MockitoExtension} showcases the {@link TestInstancePostProcessor}
 * and {@link ParameterResolver} extension APIs of JUnit 5 by providing
 * dependency injection support at the field level and at the method parameter
 * level via Mockito 2.x's {@link Mock @Mock} annotation.
 *
 * @since 5.0
 */
public class MockitoExtension implements TestInstancePostProcessor, ParameterResolver {

	/**
	 * @see org.junit.jupiter.api.extension.ParameterResolver#supportsParameter(org.junit.jupiter.api.extension.ParameterContext,
	 *      org.junit.jupiter.api.extension.ExtensionContext)
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return parameterContext.getParameter().isAnnotationPresent(Mock.class);
	}

	/**
	 * @see org.junit.jupiter.api.extension.ParameterResolver#resolveParameter(org.junit.jupiter.api.extension.ParameterContext,
	 *      org.junit.jupiter.api.extension.ExtensionContext)
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return getMock(parameterContext.getParameter(), extensionContext);
	}

	/**
	 * @see org.junit.jupiter.api.extension.TestInstancePostProcessor#postProcessTestInstance(java.lang.Object,
	 *      org.junit.jupiter.api.extension.ExtensionContext)
	 */
	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		MockitoAnnotations.initMocks(testInstance);
	}

	private Object getMock(Parameter parameter, ExtensionContext extensionContext) {
		Class<?> mockType = parameter.getType();
		Store mocks = extensionContext.getStore(Namespace.create(
			MockitoExtension.class, mockType));
		String mockName = getMockName(parameter);

		if (mockName != null) {
			return mocks.getOrComputeIfAbsent(
				mockName, key -> mock(mockType, mockName));
		} else {
			return mocks.getOrComputeIfAbsent(
				mockType.getCanonicalName(), key -> mock(mockType));
		}
	}

	private String getMockName(Parameter parameter) {
		String explicitMockName = parameter.getAnnotation(Mock.class).name().trim();
		
		if (!explicitMockName.isEmpty()) {
			return explicitMockName;
		} else if (parameter.isNamePresent()) {
			return parameter.getName();
		}
		return null;
	}
}

