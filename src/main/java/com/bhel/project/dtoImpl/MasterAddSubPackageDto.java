package com.bhel.project.dtoImpl;

import javax.validation.constraints.NotEmpty;

public class MasterAddSubPackageDto {

	@NotEmpty(message = "package name can not be empty!!")
	private String SUB_PACKAGE_NAME;

	public String getSUB_PACKAGE_NAME() {
		return SUB_PACKAGE_NAME;
	}

	public void setSUB_PACKAGE_NAME(String sUB_PACKAGE_NAME) {
		SUB_PACKAGE_NAME = sUB_PACKAGE_NAME;
	}

	@Override
	public String toString() {
		return "MasterAddSubPackageDto [SUB_PACKAGE_NAME=" + SUB_PACKAGE_NAME + "]";
	}
	
	public MasterAddSubPackageDto() {
	}
	
}
