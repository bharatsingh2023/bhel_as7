package com.bhel.project.entityImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

@Entity
public class BHEL_SUB_PACKAGES {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BHELSUBPACKAGES")
	@SequenceGenerator(name = "BHELSUBPACKAGES" ,sequenceName = "BHEL_SUB_PACKAGES_SEQ",allocationSize = 1)
    @Column(name="SUB_PACKAGES_ID")
	private int Id;
    
	@NotEmpty(message = "Sub-Package name can not be empty!!")
    @Column(name="SUB_PACKAGES_NAME")
	private String BHEL_SUB_PACKAGE_NAME;

	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getBHEL_SUB_PACKAGE_NAME() {
		return BHEL_SUB_PACKAGE_NAME;
	}
	public void setBHEL_SUB_PACKAGE_NAME(String bHEL_SUB_PACKAGE_NAME) {
		BHEL_SUB_PACKAGE_NAME = bHEL_SUB_PACKAGE_NAME;
	}
	
	public BHEL_SUB_PACKAGES() {
	}
	
}
