package com.giong.web.validator;

import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	@Autowired
	MessageService messageService;

	@Override
	public boolean supports(Class<?> clazz) {
		return MtEmployee.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		final MtEmployee currentEMployee = (MtEmployee) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeCode",
			this.messageService.getMessages("validator.employee_code_is_required"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeName",
			this.messageService.getMessages("validator.employee_name_is_required"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth",
			this.messageService.getMessages("validator.dob_is_required"));

		if (!StringUtils.isEmpty(currentEMployee.getEmail()) && !this.emailValidator
			.valid(currentEMployee.getEmail())) {
			errors.rejectValue("email", this.messageService.getMessages("validator.email_is_not_well_formed"));
		}
	}

}
