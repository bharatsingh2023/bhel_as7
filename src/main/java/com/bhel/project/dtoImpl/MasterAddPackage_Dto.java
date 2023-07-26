package com.bhel.project.dtoImpl;
import javax.validation.constraints.NotEmpty;
public class MasterAddPackage_Dto {

	@NotEmpty(message = "package name can not be empty!!")
	private String PACKAGE_NAME;
	/* @NotEmpty(message = "Vendor name can not be empty!!") */
	private String VendorName;
	
	
	
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}
	public String getPACKAGE_NAME() {
		return PACKAGE_NAME;
		}
	public void setPACKAGE_NAME(String pACKAGE_NAME) {
		PACKAGE_NAME = pACKAGE_NAME;
	}
	@Override
	public String toString() {
		return "MasterAddPackage_Dto [PACKAGE_NAME=" + PACKAGE_NAME + ", VendorName=" + VendorName + "]";
	}
	
	
	
}
