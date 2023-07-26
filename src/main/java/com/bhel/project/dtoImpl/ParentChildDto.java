package com.bhel.project.dtoImpl;

public class ParentChildDto {

	private String parentId;
	private String childId;
	private String grandchildId;
	private String grandgrandchildId;
	private String sth_number;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public String getGrandchildId() {
		return grandchildId;
	}

	public void setGrandchildId(String grandchildId) {
		this.grandchildId = grandchildId;
	}

	public String getGrandgrandchildId() {
		return grandgrandchildId;
	}

	public void setGrandgrandchildId(String grandgrandchildId) {
		this.grandgrandchildId = grandgrandchildId;
	}

	public String getSth_number() {
		return sth_number;
	}

	public void setSth_number(String sth_number) {
		this.sth_number = sth_number;
	}

	public ParentChildDto(String parentId, String childId, String grandchildId, String grandgrandchildId) {
		super();
		this.parentId = parentId;
		this.childId = childId;
		this.grandchildId = grandchildId;
		this.grandgrandchildId = grandgrandchildId;
	}

	public ParentChildDto(String parentId, String childId, String grandchildId, String grandgrandchildId,
			String sth_number) {
		super();
		this.parentId = parentId;
		this.childId = childId;
		this.grandchildId = grandchildId;
		this.grandgrandchildId = grandgrandchildId;
		this.sth_number = sth_number;
	}

	public ParentChildDto() {
	}
	
	
	@Override
	public String toString() {
		return "ParentChildDto [parentId=" + parentId + ", childId=" + childId + ", grandchildId=" + grandchildId
				+ ", grandgrandchildId=" + grandgrandchildId + ", sth_number=" + sth_number + "]";
	}

}
