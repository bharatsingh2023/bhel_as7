package com.bhel.project.util;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadValidator implements ConstraintValidator<MultipartValidator,MultipartFile> {

	
	static final Logger logger = Logger.getLogger(FileUploadValidator.class);
	
	  public List<String> validExtensions = Arrays.asList(".xls", ".xlsx");

	    @Override
	    public void initialize(MultipartValidator constraintAnnotation) {
	    }

	    @Override
	    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
	    	
	        if (file.getSize() > 1000000) {
	            return false;
	        }

	       
	        String contentType = file.getContentType();
	        if (!contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") &&
	            !contentType.equals("application/vnd.ms-excel")) {
	            return false;
	        }

	        return true;
	    }
		
	/*
	 * public boolean isValid(String value, ConstraintValidatorContext context) {
	 * 
	 * logger.info("Message from isValid : {} ");
	 * 
	 * if(value.isBlank()) { return false;
	 * 
	 * }else { return true; }
	 * 
	 * }
	 */

}
