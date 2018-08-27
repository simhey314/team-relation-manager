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
package com.heyden.teamrelationmanager.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.heyden.teamrelationmanager.validation.EmailConstraintValidator;

@Constraint(validatedBy = EmailConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {

	public String message() default "{emailadress.value.violation}";

	public String pattern() default "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,8}$";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

}
