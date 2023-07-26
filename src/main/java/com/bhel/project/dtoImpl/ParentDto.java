package com.bhel.project.dtoImpl;

import java.util.List;

public class ParentDto {
	public String name;
	public String value;
	public String description;
	public Double quantity;
	public Double rate;
	public Double ammount;

	public List<ChildrenDto> childList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ChildrenDto> getChildList() {
		return childList;
	}

	public void setChildList(List<ChildrenDto> childList) {
		this.childList = childList;
	}

	public Double getRate() {
		return rate;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public Double getQuantity() {
		return quantity;
	}

	

//	public Map<String, List<Children>> getChildList() {
//		return childList;
//	}
//
//	public void setChildList(Map<String, List<Children>> childList) {
//		this.childList = childList;
//	}

}
