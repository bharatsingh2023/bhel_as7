package com.bhel.project.dtoImpl;

import java.util.List;

public class ChildrenDto {

	public String name;
	public String value;
	public String description;
	public String parent;
	public Double rate;
	public Double quantity;
	public Double ammount;
	public List<GrandChildrenDto> grandChild;

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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<GrandChildrenDto> getGrandChild() {
		return grandChild;
	}

	public void setGrandChild(List<GrandChildrenDto> grandChild) {
		this.grandChild = grandChild;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

}
