package com.backend.fcfm.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = telefonoValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface Telefono {
	String message() default "El telefono debe tener 10 digitos";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
