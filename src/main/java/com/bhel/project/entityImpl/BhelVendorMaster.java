package com.bhel.project.entityImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class BhelVendorMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BHELCUSTOMERMASTERSEQ")
	@SequenceGenerator(name = "BHELCUSTOMERMASTERSEQ", sequenceName = "BHEL_CUSTOMER_MASTER_SEQ", allocationSize = 1)
	private int ID;
	@NotEmpty(message = "Vendor Name can not be empty!!")
	private String VENDOR_NAME;
	@Size(min = 10, message = "Enter atleast 10 Digit!! ")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Fill the valid contact number!!")
	@Column(unique = true)
	private String VENDOR_CONTACT_NO;
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address")
	private String EMAIL_ID;
	@NotEmpty(message = "Address can not be empty")
	private String ADDRESS;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVENDOR_NAME() {
		return VENDOR_NAME;
	}

	public void setVENDOR_NAME(String vENDOR_NAME) {
		VENDOR_NAME = vENDOR_NAME;
	}

	public String getVENDOR_CONTACT_NO() {
		return VENDOR_CONTACT_NO;
	}

	public void setVENDOR_CONTACT_NO(String vENDOR_CONTACT_NO) {
		VENDOR_CONTACT_NO = vENDOR_CONTACT_NO;
	}

	public String getEMAIL_ID() {
		return EMAIL_ID;
	}

	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	@Override
	public String toString() {
		return "BHEL_VENDOR_MASTER [ID=" + ID + ", VENDOR_NAME=" + VENDOR_NAME + ", VENDOR_CONTACT_NO="
				+ VENDOR_CONTACT_NO + ", EMAIL_ID=" + EMAIL_ID + ", ADDRESS=" + ADDRESS + "]";
	}

	public BhelVendorMaster(int iD, @NotEmpty(message = "Vendor Name can not be empty!!") String vENDOR_NAME,
			@Size(min = 10, message = "Enter atleast 10 Digit!! ") @Pattern(regexp = "(^$|[0-9]{10})", message = "Fill the valid contact number!!") String vENDOR_CONTACT_NO,
			@NotEmpty @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address") String eMAIL_ID,
			@NotEmpty(message = "Address can not be empty") String aDDRESS) {
		super();
		ID = iD;
		VENDOR_NAME = vENDOR_NAME;
		VENDOR_CONTACT_NO = vENDOR_CONTACT_NO;
		EMAIL_ID = eMAIL_ID;
		ADDRESS = aDDRESS;
	}

	public BhelVendorMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

}
