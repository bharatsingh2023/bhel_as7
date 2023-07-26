package com.bhel.project.entityImpl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "BHEL_PACKAGES")
public class BHEL_PACKAGES {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BhelPackagesGenerator")
	@SequenceGenerator(name = "BhelPackagesGenerator", sequenceName = "BHEL_PACKAGES_SEQ", allocationSize = 1)
	private int PKG_ID;
	
	private String PACKAGE_NAME;
	private String VENDOR_NAME;

	public String getVENDOR_NAME() {
		return VENDOR_NAME;
	}
	public void setVENDOR_NAME(String vENDOR_NAME) {
		VENDOR_NAME = vENDOR_NAME;
	}
	public BHEL_PACKAGES() {
		super();
	}
	public int getPKG_ID() {
		return PKG_ID;
	}
	public void setPKG_ID(int pKG_ID) {
		PKG_ID = pKG_ID;
	}
	public String getPACKAGE_NAME() {
		return PACKAGE_NAME;
	}
	public void setPACKAGE_NAME(String pACKAGE_NAME) {
		PACKAGE_NAME = pACKAGE_NAME;
	}

}
