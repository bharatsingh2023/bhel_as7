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
@Table(name = "BHEL_CUSTOMER_PROJECT_MASTER")
public class CustomerVendorData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cvm_sequence")
	@SequenceGenerator(name = "cvm_sequence", sequenceName = "BHEL_CUSTOMER_VENDOR_SEQ", allocationSize = 1)
	@Column(name = "CVM_ID")
	private int cvm_id;

	@Column(name = "PROJECT_CODE")
	private String projectCode;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "CCN")
	private String cCn;

	@Column(name = "CUSTOMER_NAME")
	private String customer_name;

	@Column(name = "TOTAL_PROJ_VALUE")
	private Double totalProjValue;

	@Column(name = "CUSTOMER_BBU_PRESENT", columnDefinition = "integer")
	private int custBbuPresent;

	@Column(name = "VENDOR_BBU_PRESENT", columnDefinition = "integer")
	private int vendorBbuPresent;

	public int getCustBbuPresent() {
		return custBbuPresent;
	}

	public void setCustBbuPresent(int custBbuPresent) {
		this.custBbuPresent = custBbuPresent;
	}

	public int getVendorBbuPresent() {
		return vendorBbuPresent;
	}

	public void setVendorBbuPresent(int vendorBbuPresent) {
		this.vendorBbuPresent = vendorBbuPresent;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getTotalProjValue() {
		return totalProjValue;
	}

	public void setTotalProjValue(Double totalProjValue) {
		this.totalProjValue = totalProjValue;
	}

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

	public int getcvm_id() {
		return cvm_id;
	}

	public void setcvm_id(int cvm_id) {
		this.cvm_id = cvm_id;
	}

	public int getCvm_id() {
		return cvm_id;
	}

	public void setCvm_id(int cvm_id) {
		this.cvm_id = cvm_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public CustomerVendorData() {
		super();
		// TODO Auto-generated constructor stub
	}

}
