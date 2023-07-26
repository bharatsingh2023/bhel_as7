package com.bhel.project.entityImpl;

import java.util.List;

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
@Table(name = "BHEL_BBU_TO_WAM")
public class BbuToWam {

	@Id
	@Column(name = "ID")
	@JsonSerialize(using = ToStringSerializer.class)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
	@SequenceGenerator(name = "id_sequence", sequenceName = "bhel_bbu_to_wam_sequence", allocationSize = 1)
	private int sl_no;
	
	@Column(name = "CPC")
	@JsonSerialize(using = ToStringSerializer.class)
	private long cpc;
	
	@Column(name = "CCN")
	@JsonSerialize(using = ToStringSerializer.class)
	private long ccn;
	@Column(name = "BBU_BOX_ID")
	private String leftBoxId;
	@Transient
	private String leftWorkName;
	@Column(name = "BBU_QUANTITY")
	@JsonSerialize(using = ToStringSerializer.class)
	private int leftQuantity;
	@Column(name = "BBU_DESCRIPTION")

	private String leftDescription;
	@Column(name = "PATH_NAME")
	private String leftPathName;
	@Column(name = "BBU_RATE")
	@JsonSerialize(using = ToStringSerializer.class)
	private int leftRate;

	@Column(name = "BBU_AMOUNT")
	@JsonSerialize(using = ToStringSerializer.class)
	private int leftAmmount;
	@Column(name = "WAM_BOX_ID")
	private String rightBoxId;

	@Transient
	private String rightWorkName;
	@Column(name = "WAM_QUANTITY")
	private String rightQuantity;
	@Column(name = "WAM_DESCRIPTION")

	private String rightDescription;

	@Transient
	private String rightPathName;
	@Column(name = "WAM_RATE")
	private String rightRate;
	@Column(name = "WAM_AMOUNT")
	@JsonSerialize(using = ToStringSerializer.class)
	private int rightAmmount;

	@Column(name = "BBU_ID")
	// @Transient
	@JsonSerialize(using = ToStringSerializer.class)
	private String spanIdArrayL;
	@Column(name = "WAM_ID")
	// @Transient
	@JsonSerialize(using = ToStringSerializer.class)
	private String spanIdArrayR;
	@Column(name = "WORK_DONE")
	// @Transient
	@JsonSerialize(using = ToStringSerializer.class)
	private String workDone;

	@Column(name = "PKG_ID")
	private String PkgID;
	@Column(name = "WORK_AREA")
	private String workArea;

	public long getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public long getCpc() {
		return cpc;
	}

	public void setCpc(long cpc) {
		this.cpc = cpc;
	}

	public void setCcn(long ccn) {
		this.ccn = ccn;
	}

	public long getCcn() {
		return ccn;
	}

	public String getLeftBoxId() {
		return leftBoxId;
	}

	public void setLeftBoxId(String leftBoxId) {
		this.leftBoxId = leftBoxId;
	}

	public String getLeftWorkName() {
		return leftWorkName;
	}

	public void setLeftWorkName(String leftWorkName) {
		this.leftWorkName = leftWorkName;
	}

	public int getLeftQuantity() {
		return leftQuantity;
	}

	public void setLeftQuantity(int leftQuantity) {
		this.leftQuantity = leftQuantity;
	}

	public String getLeftDescription() {
		return leftDescription;
	}

	public void setLeftDescription(String leftDescription) {
		this.leftDescription = leftDescription;
	}

	public String getLeftPathName() {
		return leftPathName;
	}

	public void setLeftPathName(String leftPathName) {
		this.leftPathName = leftPathName;
	}

	public int getLeftRate() {
		return leftRate;
	}

	public void setLeftRate(int leftRate) {
		this.leftRate = leftRate;
	}

	public String getRightBoxId() {
		return rightBoxId;
	}

	public void setRightBoxId(String rightBoxId) {
		this.rightBoxId = rightBoxId;
	}

	public String getRightWorkName() {
		return rightWorkName;
	}

	public void setRightWorkName(String rightWorkName) {
		this.rightWorkName = rightWorkName;
	}

	public String getRightQuantity() {
		return rightQuantity;
	}

	public void setRightQuantity(String rightQuantity) {
		this.rightQuantity = rightQuantity;
	}

	public String getRightDescription() {
		return rightDescription;
	}

	public void setRightDescription(String rightDescription) {
		this.rightDescription = rightDescription;
	}

	public String getRightRate() {
		return rightRate;
	}

	public void setRightRate(String rightRate) {
		this.rightRate = rightRate;
	}

	public BbuToWam() {
	}

	public String getRightPathName() {
		return rightPathName;
	}

	public void setRightPathName(String rightPathName) {
		this.rightPathName = rightPathName;
	}

	public int getLeftAmmount() {
		return leftAmmount;
	}

	public void setLeftAmmount(int leftAmmount) {
		this.leftAmmount = leftAmmount;
	}

	public int getRightAmmount() {
		return rightAmmount;
	}

	public void setRightAmmount(int rightAmmount) {
		this.rightAmmount = rightAmmount;
	}

	public String getSpanIdArrayL() {
		return spanIdArrayL;
	}

	public void setSpanIdArrayL(String spanIdArrayL) {
		this.spanIdArrayL = spanIdArrayL;
	}

	public String getSpanIdArrayR() {
		return spanIdArrayR;
	}

	public void setSpanIdArrayR(String spanIdArrayR) {
		this.spanIdArrayR = spanIdArrayR;
	}

	public String getWorkDone() {
		return workDone;
	}

	public void setWorkDone(String workDone) {
		this.workDone = workDone;
	}

	public String getPkgID() {
		return PkgID;
	}

	public void setPkgID(String pkgID) {
		PkgID = pkgID;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public BbuToWam(int sl_no, long cpc, long ccn, String leftBoxId, String leftWorkName, int leftQuantity,
			String leftDescription, String leftPathName, int leftRate, int leftAmmount, String rightBoxId,
			String rightWorkName, String rightQuantity, String rightDescription, String rightPathName, String rightRate,
			int rightAmmount, String spanIdArrayL, String spanIdArrayR, String workDone, String pkgID,
			String workArea) {
		super();
		this.sl_no = sl_no;
		this.cpc = cpc;
		this.ccn = ccn;
		this.leftBoxId = leftBoxId;
		this.leftWorkName = leftWorkName;
		this.leftQuantity = leftQuantity;
		this.leftDescription = leftDescription;
		this.leftPathName = leftPathName;
		this.leftRate = leftRate;
		this.leftAmmount = leftAmmount;
		this.rightBoxId = rightBoxId;
		this.rightWorkName = rightWorkName;
		this.rightQuantity = rightQuantity;
		this.rightDescription = rightDescription;
		this.rightPathName = rightPathName;
		this.rightRate = rightRate;
		this.rightAmmount = rightAmmount;
		this.spanIdArrayL = spanIdArrayL;
		this.spanIdArrayR = spanIdArrayR;
		this.workDone = workDone;
		PkgID = pkgID;
		this.workArea = workArea;
	}

}
