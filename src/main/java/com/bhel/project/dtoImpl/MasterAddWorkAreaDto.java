package com.bhel.project.dtoImpl;

import javax.validation.constraints.NotEmpty;

public class MasterAddWorkAreaDto {

	@NotEmpty(message = "Work area name can not be empty!! ")
	private String WORK_AREA_NAME;

	public String getWORK_AREA_NAME() {
		return WORK_AREA_NAME;
	}

	public void setWORK_AREA_NAME(String wORK_AREA_NAME) {
		WORK_AREA_NAME = wORK_AREA_NAME;
	}
	
	public MasterAddWorkAreaDto() {
		// TODO Auto-generated constructor stub
	}
	
}
