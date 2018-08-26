package com.heyden.teamrelationmanager.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.heyden.teamrelationmanager.validation.EmailConstraintValidator;

@Constraint(validatedBy=EmailConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {
	
	public String message() default "{emailadress.value.violation}";
	
	public String pattern() default "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,8}$";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};

}
