package com.bhel.project.entityImpl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "bhel_customer_bbu_gt")
public class BhelBbuData {
	@Id
	@Column(name = "ID")
	private Integer BBU_ID;
	@Transient
	private String TYPE;
	private String ST_NO_1;
	private String ST_NO_2;
	private String ST_NO_3;
	private String ST_NO_4;
	private String ITEM_DESCRIPTION;
	private String UNIT;
	@Column(name = "TOTAL_QTY", columnDefinition = "varchar default '0'")
	private Integer QUANTITY;
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	@Column(name = "LOI_NO")
	private String loiNo;
	@Column(name = "WO_NO")
	private String WO_REF;

	@Column(name = "CONTRACT_PRICE")
	private String contractPrice;
	@Column(name = "ALLOCATED_PERCENTAGE")
	private String alocPercentage;

	@Column(name = "UNIT_RATE", columnDefinition = "varchar default '0'")
	private String RATE;
	@Column(name = "AGREED_RATE")
	private String agreedRate;
	@Column(name = "AGREED_AMT_QTY_INCRE")
	private String agreedAmtQtyIncre;
	@Column(name = "AGREED_AMT_WO")
	private String agreedAmtWo;
	private String CPC;

	@Column(columnDefinition = "varchar default '0'")
	private Integer CUM_QTY;
	@Column(name = "CUM_VALUE", columnDefinition = "varchar default '0'")
	private Integer CUM_AMT;
	private String PROJECT_NAME;
	private String CUSTOMER_CONTACT_NO;
	@Column(name = "BBU_CREATE_DATE")
	private Date bbuCreateDate;
	@Column(name = "EC_UPDATE_DATE")
	private Date ecUpdateDate;
	@Column(name = "WO_QTY")
	private String woQty;
	@Column(name = "INCREASED_QTY")
	private String increasedQty;

	public Integer getBBU_ID() {
		return BBU_ID;
	}

	public void setBBU_ID(Integer bBU_ID) {
		BBU_ID = bBU_ID;
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

	public String getITEM_DESCRIPTION() {
		return ITEM_DESCRIPTION;
	}

	public void setITEM_DESCRIPTION(String iTEM_DESCRIPTION) {
		ITEM_DESCRIPTION = iTEM_DESCRIPTION;
	}

	public String getUNIT() {
		return UNIT;
	}

	public void setUNIT(String uNIT) {
		UNIT = uNIT;
	}

	public int getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(int qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public String getWO_REF() {
		return WO_REF;
	}

	public void setWO_REF(String wO_REF) {
		WO_REF = wO_REF;
	}

	public String getCPC() {
		return CPC;
	}

	public void setCPC(String cPC) {
		CPC = cPC;
	}

	public int getCUM_QTY() {
		return CUM_QTY;
	}

	public void setCUM_QTY(int cUM_QTY) {
		CUM_QTY = cUM_QTY;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLoiNo() {
		return loiNo;
	}

	public void setLoiNo(String loiNo) {
		this.loiNo = loiNo;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getAlocPercentage() {
		return alocPercentage;
	}

	public void setAlocPercentage(String alocPercentage) {
		this.alocPercentage = alocPercentage;
	}

	public String getAgreedRate() {
		return agreedRate;
	}

	public void setAgreedRate(String agreedRate) {
		this.agreedRate = agreedRate;
	}

	public String getAgreedAmtQtyIncre() {
		return agreedAmtQtyIncre;
	}

	public void setAgreedAmtQtyIncre(String agreedAmtQtyIncre) {
		this.agreedAmtQtyIncre = agreedAmtQtyIncre;
	}

	public String getAgreedAmtWo() {
		return agreedAmtWo;
	}

	public void setAgreedAmtWo(String agreedAmtWo) {
		this.agreedAmtWo = agreedAmtWo;
	}

	public String getRATE() {
		return RATE;
	}

	public void setRATE(String rATE) {
		RATE = rATE;
	}

	public Integer getCUM_AMT() {
		return CUM_AMT;
	}

	public void setCUM_AMT(Integer cUM_AMT) {
		CUM_AMT = cUM_AMT;
	}

	public void setQUANTITY(Integer qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public void setCUM_QTY(Integer cUM_QTY) {
		CUM_QTY = cUM_QTY;
	}

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

	public Date getBbuCreateDate() {
		return bbuCreateDate;
	}

	public void setBbuCreateDate(Date bbuCreateDate) {
		this.bbuCreateDate = bbuCreateDate;
	}

	public Date getEcUpdateDate() {
		return ecUpdateDate;
	}

	public void setEcUpdateDate(Date ecUpdateDate) {
		this.ecUpdateDate = ecUpdateDate;
	}

	public String getWoQty() {
		return woQty;
	}

	public void setWoQty(String woQty) {
		this.woQty = woQty;
	}

	public String getIncreasedQty() {
		return increasedQty;
	}

	public void setIncreasedQty(String increasedQty) {
		this.increasedQty = increasedQty;
	}

	public BhelBbuData() {
		super();
	}

	public BhelBbuData(Integer bBU_ID, String sT_NO_1, String sT_NO_2, String sT_NO_3, String sT_NO_4,
			String iTEM_DESCRIPTION, String uNIT, int qUANTITY, String wO_REF, String cPC, int cUM_QTY, Integer cUM_AMT,
			String pROJECT_NAME, String cUSTOMER_CONTACT_NO) {
		super();
		BBU_ID = bBU_ID;
		ST_NO_1 = sT_NO_1;
		ST_NO_2 = sT_NO_2;
		ST_NO_3 = sT_NO_3;
		ST_NO_4 = sT_NO_4;
		ITEM_DESCRIPTION = iTEM_DESCRIPTION;
		UNIT = uNIT;
		QUANTITY = qUANTITY;
		WO_REF = wO_REF;
		CPC = cPC;
		CUM_QTY = cUM_QTY;
		CUM_AMT = cUM_AMT;
		PROJECT_NAME = pROJECT_NAME;
		CUSTOMER_CONTACT_NO = cUSTOMER_CONTACT_NO;
	}

	@Override
	public String toString() {
		return "BhelBbuData [BBU_ID=" + BBU_ID + ", TYPE=" + TYPE + ", ST_NO_1=" + ST_NO_1 + ", ST_NO_2=" + ST_NO_2
				+ ", ST_NO_3=" + ST_NO_3 + ", ST_NO_4=" + ST_NO_4 + ", ITEM_DESCRIPTION=" + ITEM_DESCRIPTION + ", UNIT="
				+ UNIT + ", QUANTITY=" + QUANTITY + ", customerName=" + customerName + ", loiNo=" + loiNo + ", WO_REF="
				+ WO_REF + ", contractPrice=" + contractPrice + ", alocPercentage=" + alocPercentage + ", RATE=" + RATE
				+ ", agreedRate=" + agreedRate + ", agreedAmtQtyIncre=" + agreedAmtQtyIncre + ", agreedAmtWo="
				+ agreedAmtWo + ", CPC=" + CPC + ", CUM_QTY=" + CUM_QTY + ", CUM_AMT=" + CUM_AMT + ", PROJECT_NAME="
				+ PROJECT_NAME + ", CUSTOMER_CONTACT_NO=" + CUSTOMER_CONTACT_NO + ", bbuCreateDate=" + bbuCreateDate
				+ ", ecUpdateDate=" + ecUpdateDate + ", woQty=" + woQty + ", increasedQty=" + increasedQty + "]";
	}

	
}
