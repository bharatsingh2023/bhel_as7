package com.bhel.project.dtoImpl;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

public class BBUWAMDto {
	// @NotEmpty(message = "Project should not be empty")
	private String CPC;
	private String PROJECT_NAME;
	private String CUSTOMER_CONTACT_NO;

	private String PKG_ID;

	private String SUB_PKG_NAME;

	private String WORK_AREA;

	private String vendorName;;

	private String bbuDate;

	private String wamDate;

	private String cusBbuDate;

	private String ecDate;

	// @MultipartValidator(message = "Customer_BBU-not uploaded!!")
	private MultipartFile customerBBU;

	// @MultipartValidator(message = "Vendor_BBU-not uploaded!!")
	private MultipartFile vendorBBU;

	// @MultipartValidator(message = "Customer_BBU-not uploaded!!")
	private MultipartFile customerEc;

	// @MultipartValidator(message = "Vendor_BBU-not uploaded!!")
	private MultipartFile vendorWam;

	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}

	public String getCUSTOMER_CONTACT_NO() {
		return CUSTOMER_CONTACT_NO;
	}

	public void setCUSTOMER_CONTACT_NO(String cUSTOMER_CONTACT_NO) {
		CUSTOMER_CONTACT_NO = cUSTOMER_CONTACT_NO;
	}

	public String getPKG_ID() {
		return PKG_ID;
	}

	public void setPKG_ID(String pKG_ID) {
		PKG_ID = pKG_ID;
	}

	public String getSUB_PKG_NAME() {
		return SUB_PKG_NAME;
	}

	public void setSUB_PKG_NAME(String sUB_PKG_NAME) {
		SUB_PKG_NAME = sUB_PKG_NAME;
	}

	public String getWORK_AREA() {
		return WORK_AREA;
	}

	public void setWORK_AREA(String wORK_AREA) {
		WORK_AREA = wORK_AREA;
	}

	public String getBbuDate() {
		return bbuDate;
	}

	public void setBbuDate(String bbuDate) {
		this.bbuDate = bbuDate;
	}

	public String getWamDate() {
		return wamDate;
	}

	public void setWamDate(String wamDate) {
		this.wamDate = wamDate;
	}

	public MultipartFile getCustomerBBU() {
		return customerBBU;
	}

	public void setCustomerBBU(MultipartFile customerBBU) {
		this.customerBBU = customerBBU;
	}

	public MultipartFile getVendorBBU() {
		return vendorBBU;
	}

	public void setVendorBBU(MultipartFile vendorBBU) {
		this.vendorBBU = vendorBBU;
	}

	public String getCPC() {
		return CPC;
	}

	public void setCPC(String cPC) {
		CPC = cPC;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public MultipartFile getCustomerEc() {
		return customerEc;
	}

	public void setCustomerEc(MultipartFile customerEc) {
		this.customerEc = customerEc;
	}

	public MultipartFile getVendorWam() {
		return vendorWam;
	}

	public void setVendorWam(MultipartFile vendorWam) {
		this.vendorWam = vendorWam;
	}

	public String getCusBbuDate() {
		return cusBbuDate;
	}

	public void setCusBbuDate(String cusBbuDate) {
		this.cusBbuDate = cusBbuDate;
	}

	public String getEcDate() {
		return ecDate;
	}

	public void setEcDate(String ecDate) {
		this.ecDate = ecDate;
	}

	public BBUWAMDto(MultipartFile customerBBU, MultipartFile vendorBBU) {
		super();
		this.customerBBU = customerBBU;
		this.vendorBBU = vendorBBU;
	}

	public BBUWAMDto(String cPC, String pROJECT_NAME, String cUSTOMER_CONTACT_NO, String pKG_ID, String sUB_PKG_NAME,
			String wORK_AREA, String vendorName, String bbuDate, String wamDate, MultipartFile customerBBU,
			MultipartFile vendorBBU, MultipartFile customerEc, MultipartFile vendorWam) {
		super();
		CPC = cPC;
		PROJECT_NAME = pROJECT_NAME;
		CUSTOMER_CONTACT_NO = cUSTOMER_CONTACT_NO;
		PKG_ID = pKG_ID;
		SUB_PKG_NAME = sUB_PKG_NAME;
		WORK_AREA = wORK_AREA;
		this.vendorName = vendorName;
		this.bbuDate = bbuDate;
		this.wamDate = wamDate;
		this.customerBBU = customerBBU;
		this.vendorBBU = vendorBBU;
		this.customerEc = customerEc;
		this.vendorWam = vendorWam;
	}

	public BBUWAMDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BBUWAMDto [CPC=" + CPC + ", PROJECT_NAME=" + PROJECT_NAME + ", CUSTOMER_CONTACT_NO="
				+ CUSTOMER_CONTACT_NO + ", PKG_ID=" + PKG_ID + ", SUB_PKG_NAME=" + SUB_PKG_NAME + ", WORK_AREA="
				+ WORK_AREA + ", vendorName=" + vendorName + ", bbuDate=" + bbuDate + ", wamDate=" + wamDate
				+ ", customerBBU=" + customerBBU + ", vendorBBU=" + vendorBBU + ", customerEc=" + customerEc
				+ ", vendorWam=" + vendorWam + "]";
	}

	public void valiString(Object target, Errors errors) {
		BBUWAMDto dto = (BBUWAMDto) target;

		// Add additional custom validations specific to BBUWAMDto if needed }

	}

}
