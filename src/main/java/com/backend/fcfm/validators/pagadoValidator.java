package com.backend.fcfm.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class pagadoValidator implements ConstraintValidator<pagado,Integer>{

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value!=null) {
			return value == 0 || value ==1;
		}
		return false;
	}

}
