package com.bhel.project.entityImpl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.bhel.project.entity.BhelWorkAreaInt;

@Entity
@Table(name="BHEL_WORK_AREA")
public class BhelWorkArea implements BhelWorkAreaInt{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BhelWorkArea")
	@SequenceGenerator(name = "BhelWorkArea", sequenceName = "BHEL_WORK_AREA_SEQ" ,allocationSize = 1)
	private int WORK_AREA_ID;
	
	@NotEmpty(message = "Work area name can not be empty!! ")
	private String WORK_AREA_NAME;
	
	
	public int getWORK_AREA_ID() {
		return WORK_AREA_ID;
	}
	public void setWORK_AREA_ID(int wORK_AREA_ID) {
		WORK_AREA_ID = wORK_AREA_ID;
	}
	public String getWORK_AREA_NAME() {
		return WORK_AREA_NAME;
	}
	public void setWORK_AREA_NAME(String wORK_AREA_NAME) {
		WORK_AREA_NAME = wORK_AREA_NAME;
	}
	
	
}
