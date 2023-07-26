package com.bhel.project.dtoImpl;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Bhel_Customer_Vendor_MasterDto {
	@NotEmpty
	@Min(value=10,message ="Enter atleast 10 digit")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "project code length should be 10!!")
	private String projectCode;
	@NotEmpty
	private String projectName;

	private String cCn;
	private List<String> pKg;
	private String totalProjValue;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getcCn() {
		return cCn;
	}

	public void setcCn(String cCn) {
		this.cCn = cCn;
	}

	public List<String> getpKg() {
		return pKg;
	}

	public void setpKg(List<String> pKg) {
		this.pKg = pKg;
	}

	public String getTotalProjValue() {
		return totalProjValue;
	}

	public void setTotalProjValue(String totalProjValue) {
		this.totalProjValue = totalProjValue;
	}

	@Override
	public String toString() {
		return "Bhel_Customer_Vendor_MasterDto [projectCode=" + projectCode + ", projectName=" + projectName + ", cCn="
				+ cCn + ", pKg=" + pKg + ", totalProjValue=" + totalProjValue + "]";
	}

	

}
