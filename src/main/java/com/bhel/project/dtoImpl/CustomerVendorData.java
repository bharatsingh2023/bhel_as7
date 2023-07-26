package com.bhel.project.dtoImpl;

public class CustomerVendorData {
	private int cv_id;
	private String projectCode;
	private String cCn;
	private String pKg;
	private String workArea;
	private String vendorId;
	private String vendorName;
	private String vendorCount;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getcCn() {
		return cCn;
	}

	public void setcCn(String cCn) {
		this.cCn = cCn;
	}

	public String getpKg() {
		return pKg;
	}

	public void setpKg(String pKg) {
		this.pKg = pKg;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public int getCv_id() {
		return cv_id;
	}

	public void setCv_id(int cv_id) {
		this.cv_id = cv_id;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorCount() {
		return vendorCount;
	}

	public void setVendorCount(String vendorCount) {
		this.vendorCount = vendorCount;
	}

	public CustomerVendorData(int cv_id, String projectCode, String cCn, String pKg, String workArea,
			String vendorCount) {
		super();
		this.cv_id = cv_id;
		this.projectCode = projectCode;
		this.cCn = cCn;
		this.pKg = pKg;
		this.workArea = workArea;
		this.vendorCount = vendorCount;
	}

	public CustomerVendorData(int cv_id, String projectCode, String cCn, String pKg, String workArea, String vendorId,
			String vendorName) {
		super();
		this.cv_id = cv_id;
		this.projectCode = projectCode;
		this.cCn = cCn;
		this.pKg = pKg;
		this.workArea = workArea;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	public CustomerVendorData() {
		super();
		// TODO Auto-generated constructor stub
	}

}
