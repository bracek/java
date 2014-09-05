package com.att.uchannels.validator;

import com.att.uchannels.domain.Contact;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Mirolav Katrak
 * 
 */
@Component("contactFormValidator")
public class ContactFormValidator implements Validator {
	@Override
	public boolean supports(final Class<?> clazz) {
		return Contact.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object model, final Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"required.name", "Name is required.");
	}

}