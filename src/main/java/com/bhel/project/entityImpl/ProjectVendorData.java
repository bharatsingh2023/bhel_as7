package com.bhel.project.entityImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BHEL_PROJECT_VENDOR")
public class ProjectVendorData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pv_sequence")
	@SequenceGenerator(name = "pv_sequence", sequenceName = "BHEL_PROJECT_VENDOR_SEQ", allocationSize = 1)
	@Column(name = "PV_ID")
	private int pv_id;

	@Column(name = "PROJECT_CODE")
	private String projectCode;

	@Column(name = "PKG")
	private String pKg;

	@Column(name = "WORK_AREA")
	private String workArea;

	@Column(name = "VENDOR_ID")
	private String vendorId;

	@Column(name = "VENDOR_NAME")
	private String vendorName;
	@Transient
	private String vendorCount;

	public int getPv_id() {
		return pv_id;
	}

	public void setPv_id(int pv_id) {
		this.pv_id = pv_id;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	public ProjectVendorData(String projectCode, String pKg, String vendorName) {
		super();
		this.projectCode = projectCode;
		this.pKg = pKg;
		this.vendorName = vendorName;
	}

	
	public ProjectVendorData() {
		// TODO Auto-generated constructor stub
	}
}
