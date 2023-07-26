package com.bhel.project.dtoImpl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MasterProjectDto {
	
	  @Size(min =9,message ="Minimum length is 9 !!" )
	  @NotNull()
	private String CUSTOMER_PROJECT_CODE;
	@NotEmpty(message = "Project name can not be Empty!! ")
	private String CUSTOMER_PROJECT_NAME;
	
	
	public String getCUSTOMER_PROJECT_CODE() {
		return CUSTOMER_PROJECT_CODE;
	}
	public void setCUSTOMER_PROJECT_CODE(String cUSTOMER_PROJECT_CODE) {
		CUSTOMER_PROJECT_CODE = cUSTOMER_PROJECT_CODE;
	}
	public String getCUSTOMER_PROJECT_NAME() {
		return CUSTOMER_PROJECT_NAME;
	}
	public void setCUSTOMER_PROJECT_NAME(String cUSTOMER_PROJECT_NAME) {
		CUSTOMER_PROJECT_NAME = cUSTOMER_PROJECT_NAME;
	}
	@Override
	public String toString() {
		return "MasterProjectDto [CUSTOMER_PROJECT_CODE=" + CUSTOMER_PROJECT_CODE + ", CUSTOMER_PROJECT_NAME="
				+ CUSTOMER_PROJECT_NAME + "]";
	}
	
	public MasterProjectDto() {
	}
	

}
