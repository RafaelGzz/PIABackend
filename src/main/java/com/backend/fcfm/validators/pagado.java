package com.backend.fcfm.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Constraint(validatedBy= pagadoValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface pagado {
	
	String message() default "Solo se puede introducio  0-. No pagado / 1.- Pagado";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
