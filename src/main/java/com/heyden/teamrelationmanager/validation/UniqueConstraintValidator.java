package com.heyden.teamrelationmanager.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.heyden.teamrelationmanager.service.FieldUniqueness;
import com.heyden.teamrelationmanager.validation.annotation.Unique;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object> {

	
	private FieldUniqueness service;
	private String fieldName;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Override
	public void initialize(Unique constraintAnnotation) {
		Class<? extends FieldUniqueness> clazz = constraintAnnotation.service();
		fieldName = constraintAnnotation.field();
		String serviceQualifier = constraintAnnotation.serviceQualifier();
		
		if (StringUtils.isNotEmpty(serviceQualifier)) {
			service = appContext.getBean(serviceQualifier, clazz);
		} else {
			service = appContext.getBean(clazz);
		}
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean result;
		if (value == null) {
			result = true;
		} else {
			result = !service.fieldValueExists(value, fieldName);
		}
		return result;
	}

}
