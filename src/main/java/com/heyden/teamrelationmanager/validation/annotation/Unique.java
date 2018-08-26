package com.heyden.teamrelationmanager.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.heyden.teamrelationmanager.service.FieldUniqueness;
import com.heyden.teamrelationmanager.validation.UniqueConstraintValidator;


@Constraint(validatedBy=UniqueConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
	
	public String message() default "{unique.value.violation}";
	
	public String field();
	
	public String serviceQualifier() default "";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};

	Class<? extends FieldUniqueness> service();

}
