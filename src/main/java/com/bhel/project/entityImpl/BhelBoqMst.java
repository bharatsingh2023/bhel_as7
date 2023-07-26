package com.bhel.project.entityImpl;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BHEL_BOQ_MST")
public class BhelBoqMst {
	@Id
	private String ID;
	private String ST_NO_1;
	private String ST_NO_2;
	private String ST_NO_3;
	private String ST_NO_4;
	private String QUANTITY;
	private String ITEM_DESCRIPTION;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getST_NO_1() {
		return ST_NO_1;
	}

	public void setST_NO_1(String sT_NO_1) {
		ST_NO_1 = sT_NO_1;
	}

	public String getST_NO_2() {
		return ST_NO_2;
	}

	public void setST_NO_2(String sT_NO_2) {
		ST_NO_2 = sT_NO_2;
	}

	public String getST_NO_3() {
		return ST_NO_3;
	}

	public void setST_NO_3(String sT_NO_3) {
		ST_NO_3 = sT_NO_3;
	}

	public String getST_NO_4() {
		return ST_NO_4;
	}

	public void setST_NO_4(String sT_NO_4) {
		ST_NO_4 = sT_NO_4;
	}

	public String getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public String getITEM_DESCRIPTION() {
		return ITEM_DESCRIPTION;
	}

	public void setITEM_DESCRIPTION(String iTEM_DESCRIPTION) {
		ITEM_DESCRIPTION = iTEM_DESCRIPTION;
	}

}
