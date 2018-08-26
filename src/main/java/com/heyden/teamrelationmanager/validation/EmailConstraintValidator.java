package com.heyden.teamrelationmanager.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.heyden.teamrelationmanager.validation.annotation.EmailAddress;

public class EmailConstraintValidator  implements ConstraintValidator<EmailAddress, String> {
	
	private Pattern pattern;

	@Override
	public void initialize(EmailAddress constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		this.pattern = Pattern.compile(constraintAnnotation.pattern(), Pattern.UNICODE_CHARACTER_CLASS);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean result = false;
		
		if (StringUtils.isEmpty(value)){
			result = true;
		} else {
			result = pattern.matcher(value).matches();
		}
		return result;
	}
}
