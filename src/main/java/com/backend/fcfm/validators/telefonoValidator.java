package com.backend.fcfm.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class telefonoValidator implements ConstraintValidator<Telefono, Long> {
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value != null) {
			return value >= 1000000000;
		}
		return false;
	}
}
