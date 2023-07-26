package com.bhel.project.util;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bhel.project.dtoImpl.BBUWAMDto;

public class BBUWAMDtoValidator implements Validator {
	static final Logger logger = Logger.getLogger(BBUWAMDtoValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return BBUWAMDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		try {
			BBUWAMDto dto = (BBUWAMDto) target;
			logger.info("BBUWAMDto :: " + dto + " val :: " + dto.getPROJECT_NAME());

			if (dto.getCustomerBBU() == null) {
				errors.rejectValue("ECExcel", "NotEmpty", "ECExcel not uploaded");

			}

			if (dto.getVendorBBU() == null) {
				errors.rejectValue("WAMExcel", "NotEmpty", "WAMExcel not uploaded");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
