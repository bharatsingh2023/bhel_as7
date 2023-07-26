package com.bhel.project.dtoImpl;

public class CPC_MasterDto {
	
	
	private String CUSTOMER_PROJECT_CODE;
	private String CUSTOMER_PROJECT_NAME;
	
		public CPC_MasterDto() {
		super();
	}
	public CPC_MasterDto(String cUSTOMER_PROJECT_CODE, String cUSTOMER_PROJECT_NAME) {
		super();
		CUSTOMER_PROJECT_CODE = cUSTOMER_PROJECT_CODE;
		CUSTOMER_PROJECT_NAME = cUSTOMER_PROJECT_NAME;
	}
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
	
	
}
