package com.giong.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.giong.web.persistence.mt.MtEmployee;

@Component
public class EmployeeValidator implements Validator {
	
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MtEmployee.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		final MtEmployee currentEMployee = (MtEmployee) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeCode", "validator.employee_code_is_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeName", "validator.employee_name_is_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "validator.dob_is_required");
		
		if (!this.emailValidator.valid(currentEMployee.getEmail())) {
			errors.rejectValue("email", "validator.email_is_not_well_formed");
		}
	}
	
}
