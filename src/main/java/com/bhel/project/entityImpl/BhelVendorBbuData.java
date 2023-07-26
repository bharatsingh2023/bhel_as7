package com.bhel.project.entityImpl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Entity
@Scope(value = "prototype")
@Table(name = "bhel_vendor_bbu_gt")
public class BhelVendorBbuData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wam_sequence")
	@SequenceGenerator(name = "wam_sequence", sequenceName = "bhel_wam_data_new_sequence", allocationSize = 1)
	private int ID;
	
	@Column(name = "CUSTOMER_PROJECT_CODE")
	private String CPC;
	
	@Column(name = "LOI_NO")
	private String LoiNo;
	
	@Column(name = "WO_NO")
	private String WoNo;
	
	@Column(name = " ST_NO_1")
	private String ST_NO1;
	
	@Column(name = " ST_NO_2")
	private String ST_NO2;
	
	@Column(name = "ST_NO_3")
	private String ST_NO3;
	
	@Column(name = "ST_NO_4")
	private String ST_NO4;
	
	@Column(name = "ITEM_DESCRIPTION")
	private String ItemDescription;
	
	@Column(name = "UNIT")
	private String Unit;
	
	@Column(name = "WO_QTY")
	private String WoQty;
	
	@Column(name = "INCREASED_QTY")
	private String IncreasedQty;
	
	@Column(name = "CUM_QTY")
	private String quentity;
	
	@Column(name = "CONTRACT_PRICE")
	private String contractPrice;
	
	@Column(name = "AGREED_AMT_QTY_INCRE")
	private String agreedAmtQtyIncre;
	
	@Column(name = "AGREED_AMT_WO")
	private String AgreedAmtWo;
	

	// private String CUM_QTY;
	@Column(name = "UNIT_RATE")
	private String RATE;
	@Column(name = "AGREED_RATE")
	private String agreedRATE;
	@Column(name = "ALLOCATED_PERCENTAGE")
	private String AREA_OF_ALOCATION;

	private String PROJECT_NAME;
	@Column(name = "CCN")
	private String CUSTOMER_CONTACT_NO;
	
	@Column(name = "PKG_ID")
	private String PKG_Name;
	
	@Column(name = "WORK_AREA")
	private String WORK_AREA;
	@Column(name = "CUM_VALUE")
	private String cumAmmount;
	@Column(name = "BBU_CREATE_DATE")
	private Date bbuDate;
	@Column(name = "WAM_UPDATE_DATE")
	private Date wamDate;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCPC() {
		return CPC;
	}

	public void setCPC(String cPC) {
		CPC = cPC;
	}

	public String getLoiNo() {
		return LoiNo;
	}

	public void setLoiNo(String loiNo) {
		LoiNo = loiNo;
	}

	public String getWoNo() {
		return WoNo;
	}

	public void setWoNo(String woNo) {
		WoNo = woNo;
	}

	public String getST_NO1() {
		return ST_NO1;
	}

	public void setST_NO1(String sT_NO1) {
		ST_NO1 = sT_NO1;
	}

	public String getST_NO2() {
		return ST_NO2;
	}

	public void setST_NO2(String sT_NO2) {
		ST_NO2 = sT_NO2;
	}

	public String getST_NO3() {
		return ST_NO3;
	}

	public void setST_NO3(String sT_NO3) {
		ST_NO3 = sT_NO3;
	}

	public String getST_NO4() {
		return ST_NO4;
	}

	public void setST_NO4(String sT_NO4) {
		ST_NO4 = sT_NO4;
	}

	public String getItemDescription() {
		return ItemDescription;
	}

	public void setItemDescription(String itemDescription) {
		ItemDescription = itemDescription;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getWoQty() {
		return WoQty;
	}

	public void setWoQty(String woQty) {
		WoQty = woQty;
	}

	public String getIncreasedQty() {
		return IncreasedQty;
	}

	public void setIncreasedQty(String increasedQty) {
		IncreasedQty = increasedQty;
	}

	public String getQuentity() {
		return quentity;
	}

	public void setQuentity(String quentity) {
		this.quentity = quentity;
	}

	/*
	 * public String getAllocatedPercentage() { return allocatedPercentage; }
	 * 
	 * public void setAllocatedPercentage(String allocatedPercentage) {
	 * this.allocatedPercentage = allocatedPercentage; }
	 */

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getAgreedAmtQtyIncre() {
		return agreedAmtQtyIncre;
	}

	public void setAgreedAmtQtyIncre(String agreedAmtQtyIncre) {
		this.agreedAmtQtyIncre = agreedAmtQtyIncre;
	}

	public String getAgreedAmtWo() {
		return AgreedAmtWo;
	}

	public void setAgreedAmtWo(String agreedAmtWo) {
		AgreedAmtWo = agreedAmtWo;
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

	public String getPKG_Name() {
		return PKG_Name;
	}

	public void setPKG_Name(String pKG_Name) {
		PKG_Name = pKG_Name;
	}

	public String getWORK_AREA() {
		return WORK_AREA;
	}

	public void setWORK_AREA(String wORK_AREA) {
		WORK_AREA = wORK_AREA;
	}

	public String getCumAmmount() {
		return cumAmmount;
	}

	public void setCumAmmount(String cumAmmount) {
		this.cumAmmount = cumAmmount;
	}

	public String getRATE() {
		return RATE;
	}

	public void setRATE(String rATE) {
		RATE = rATE;
	}

	public String getAgreedRATE() {
		return agreedRATE;
	}

	public void setAgreedRATE(String agreedRATE) {
		this.agreedRATE = agreedRATE;
	}

	public String getAREA_OF_ALOCATION() {
		return AREA_OF_ALOCATION;
	}

	public void setAREA_OF_ALOCATION(String aREA_OF_ALOCATION) {
		AREA_OF_ALOCATION = aREA_OF_ALOCATION;
	}

	public Date getBbuDate() {
		return bbuDate;
	}

	public void setBbuDate(Date bbuDate) {
		this.bbuDate = bbuDate;
	}

	public Date getWamDate() {
		return wamDate;
	}

	public void setWamDate(Date wamDate) {
		this.wamDate = wamDate;
	}

	public BhelVendorBbuData() {
		super();
		// TODO Auto-generated constructor stub
	}

}
