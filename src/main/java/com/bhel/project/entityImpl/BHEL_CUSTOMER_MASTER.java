package com.bhel.project.entityImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BHEL_CUSTOMER_MASTER")
public class BHEL_CUSTOMER_MASTER {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BHELCUSTOMERMASTERSEQ")
	@SequenceGenerator(name = "BHELCUSTOMERMASTERSEQ", sequenceName = "BHEL_CUSTOMER_MASTER_SEQ", allocationSize = 1)
	private int ID;
	@NotEmpty(message = "Customer Name can not be empty!!")
	private String CUSTOMET_NAME;
	@Size(min = 10, message = "Enter atleast 10 Digit!! ")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Fill the valid cpc number!!")
	@Column(name = "CUSTOMER_CONTACT_NO", unique = true)
	private String cpc;
	// @NotEmpty
	// @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid
	// email address")
	private String EMAIL_ID;
	// @NotEmpty(message = "Address can not be empty")
	private String ADDRESS;

	public BHEL_CUSTOMER_MASTER() {
		super();
	}

	public BHEL_CUSTOMER_MASTER(int iD, @NotEmpty(message = "Customer Name can not be empty!!") String cUSTOMET_NAME,
			@Size(min = 10, message = "Enter atleast 10 Digit!! ") @Pattern(regexp = "(^$|[0-9]{10})", message = "Fill the valid cpc number!!") String cpc,
			String eMAIL_ID, String aDDRESS) {
		super();
		this.ID = iD;
		this.CUSTOMET_NAME = cUSTOMET_NAME;
		this.cpc = cpc;
		this.EMAIL_ID = eMAIL_ID;
		this.ADDRESS = aDDRESS;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCUSTOMET_NAME() {
		return CUSTOMET_NAME;
	}

	public void setCUSTOMET_NAME(String cUSTOMET_NAME) {
		CUSTOMET_NAME = cUSTOMET_NAME;
	}

	public String getCpc() {
		return cpc;
	}

	public void setCpc(String cpc) {
		this.cpc = cpc;
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
		return "BHEL_CUSTOMER_MASTER [ID=" + ID + ", CUSTOMET_NAME=" + CUSTOMET_NAME + ", cpc=" + cpc + ", EMAIL_ID="
				+ EMAIL_ID + ", ADDRESS=" + ADDRESS + "]";
	}

}
